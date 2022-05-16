package starships.hostiles

import processing.core.{PApplet, PImage}
import processing.sound.SoundFile
import starships.Starships
import starships.geom._
import starships.hostiles.Combator.Shot
import starships.hostiles._
import starships.obstacles._
import starships.projectiles._
import starships.traits._
import starships.upgrades._
import starships.weapons._
import starships.world._

import scala.util.Random
case class FirstBoss(
    var location: Vec2,
    var health: Int,
    var enemyQuantity: Float
) extends Boss {

  def box: Box2 = Box2(0, 0, 50, 50)
  var isDead = false
  var time: Long = System.currentTimeMillis
  def draw(p: PApplet): Unit = {
    p.image(Combator.Stingray, location.x, location.y, 50, 50)

    p.fill(255, 0, 0)
    p.rect(100, 502, health * 20, 10)
  }

  def update(timeFactor: Float): Unit = {
    move(timeFactor)
    shoot
    checkForCollision
  }
  def shoot: Unit = {
    val currentTime = System.currentTimeMillis

    if (currentTime > time + 900) {
      World.projectilesList = MachineGunProjectile(
        Vec2(location.x - 25, location.y + 10),
        -Starships.scrollspeed.toInt
      ) :: World.projectilesList
      if (Starships.volume > 0) {
        Shot.play(1, 0.1f * Starships.volume)
      }

      time = currentTime

    }
  }
  def clamp(value: Float, max: Float) = {
    if (value > max) max
    else if (value < -max) -max
    else value
  }
  def move(timeFactor: Float): Unit = {
    location =
      location.addY(5 * (Math.signum(World.player.location.y - location.y)))

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

        if (health <= 0) Starships.score += 4
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

      if (currentTime > time + 2000) {
        Spawner.isBossFight = false
        Spawner.hasFoughtBoss = true
        World.reset
        Starships.state = GameState.Selection
      }
    }
  }

}

object FirstBoss {
  var Stingray: PImage = _
  def loadImages(p: PApplet): Unit = {
    Stingray = p.loadImage("src/main/Resources/FirstBoss.png")

  }

  var Shot: SoundFile = _
  def loadSounds(p: PApplet): Unit = {
    Shot = new SoundFile(p, "src/main/Resources/MachineGunShot.mp3")
  }

}
