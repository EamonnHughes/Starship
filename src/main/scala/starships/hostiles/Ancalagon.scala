package starships.hostiles

import processing.core.{PApplet, PImage}
import processing.sound.SoundFile
import starships.Starships
import starships.geom._
import starships.hostiles.Ancalagon.Shot
import starships.hostiles._
import starships.obstacles._
import starships.projectiles._
import starships.traits._
import starships.upgrades._
import starships.weapons._
import starships.world._

case class Ancalagon(
    var health: Int,
    var location: Vec2,
    var enemyQuantity: Float
) extends Boss {
  var goingUp = false
  var isDead = false
  var time: Long = System.currentTimeMillis

  def box: Box2 = Box2(Vec2(0, 0), Vec2(50, 50))
  def draw(p: PApplet): Unit = {
    if (!isDead) {
      p.image(Ancalagon.Ancalagon, location.x, location.y, 50, 50)
      p.fill(255, 0, 0)
      p.rect(100, 502, health * 20, 10)
    }
  }
  def shoot: Unit = {
    val currentTime = System.currentTimeMillis
    if (currentTime > time + 400) {
      World.projectilesList = MachineGunProjectile(
        Vec2(location.x + 10, location.y + 25),
        -1
      ) :: World.projectilesList
      if (Starships.volume > 0) {
        Shot.play(1, 0.1f * Starships.volume)
      }
      time = currentTime

    }
  }
  def move(timeFactor: Float): Unit = {
    if (!goingUp && box.bottom + location.y < 487) {
      location = location.addY(2 * timeFactor)
    } else if (!goingUp && box.bottom + location.y >= 487) {
      goingUp = true
    }
    if (goingUp && location.y > 25) {
      location = location.addY(-2 * timeFactor)
    } else if (goingUp && location.y <= 25) {
      goingUp = false
    }
  }
  def update(timeFactor: Float): Unit = {
    if (!isDead) {
      shoot
      move(timeFactor)
    }
    checkForCollision
  }
  def checkForCollision: Unit = {
    for (i <- World.projectilesList) {
      if (
        new Box2(
          location.x + box.left,
          location.y + box.top,
          box.width,
          box.height
        ).intersects(
          new Box2(
            i.location.x + i.box.left,
            i.location.y + i.box.top,
            i.box.width,
            i.box.height
          )
        ) && i.direction > 0
      ) {
        health -= World.player.primary.damage
        if (!i.isInstanceOf[EnergyOrb])
          World.projectilesList = World.projectilesList.filterNot(p => p == i)

      }
    }
    if (health <= 0) {
      if (!isDead) {
        Starships.score += 100

        World.explosions = Explosion(location.copy(), 0, 5) :: World.explosions

        isDead = true
      }
      for (i <- World.currentMission) {
        i.finished = true
      }
      val currentTime = System.currentTimeMillis

      if (currentTime > time + 1000) {
        Spawner.isBossFight = false
        Spawner.hasFoughtBoss = true
        World.reset
        Starships.state = GameState.Selection
      }
    }

  }

}
object Ancalagon {
  var Ancalagon: PImage = _
  def loadImages(p: PApplet): Unit = {
    Ancalagon = p.loadImage("src/main/Resources/Ancalagon.png")

  }

  var Shot: SoundFile = _
  def loadSounds(p: PApplet): Unit = {
    Shot = new SoundFile(p, "src/main/Resources/MachineGunShot.mp3")
  }

}
