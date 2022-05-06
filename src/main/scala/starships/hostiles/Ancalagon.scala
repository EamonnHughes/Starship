package starships.hostiles

import processing.core.{PApplet, PImage}
import starships.Starships
import starships.geom._
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
  var time: Long = System.currentTimeMillis

  def box: Box2 = Box2(Vec2(0, 0), Vec2(50, 50))
  def draw(p: PApplet): Unit = {
    p.image(Ancalagon.Stingray, location.x, location.y, 50, 50)
    p.fill(255, 0, 0)
    p.rect(100, 502, health * 20, 10)
  }
  def shoot: Unit = {
    val currentTime = System.currentTimeMillis
    if (currentTime > time + 400) {
      World.projectilesList = MachineGunProjectile(
        Vec2(location.x - 25, location.y + 10),
        -1,
        0f
      ) :: World.projectilesList

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
    shoot
    move(timeFactor)
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
        )
      ) {
        health -= World.player.primary.damage
        World.projectilesList = World.projectilesList.filterNot(p => p == i)

        Starships.score += World.player.primary.damage
      }
    }
    if (health <= 0) {
      Spawner.isBossFight = false
      Spawner.hasFoughtBoss = true
      Starships.score += 100
    }

  }
}
object Ancalagon {
  var Stingray: PImage = _
  def loadImages(p: PApplet): Unit = {
    Stingray = p.loadImage("src/main/Resources/Stingray.png")

  }

}
