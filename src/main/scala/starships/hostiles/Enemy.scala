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

import scala.util.Random
case class Enemy(
    var location: Vec2,
    var size: Vec2,
    var velocity: Float,
    var deceleration: Float,
    var health: Int
) extends Scrolling
    with Actor {

  def box: Box2 = Box2(location, size)

  var time: Long = System.currentTimeMillis
  def draw(p: PApplet): Unit = {
    p.image(Enemy.Stingray, location.x, location.y, size.x, size.y)
  }

  var goingUp = false
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
        Vec2(10, 10),
        -Starships.scrollspeed.toInt
      ) :: World.projectilesList

      time = currentTime

    }
  }
  def clamp(value: Float, max: Float) = {
    if (value > max) max
    else if (value < -max) -max
    else value
  }
  def move(timeFactor: Float): Unit = {
    if (!goingUp && box.bottom < 487) {
      location = location.addY(1.3f * timeFactor)
    } else if (!goingUp && box.bottom >= 487) {
      goingUp = true
    }
    if (goingUp && location.y > 25) {
      location = location.addY(-1.3f * timeFactor)
    } else if (goingUp && location.y <= 25) {
      goingUp = false
    }

  }
  def checkForCollision: Unit = {
    for (i <- World.projectilesList) {
      if (box.intersects(i.blur) && i.direction == 1) {
        health -= World.player.primary.damage
        World.projectilesList = World.projectilesList.filterNot(p => p == i)

        Starships.score += World.player.primary.damage
      }
    }
    if (health <= 0) {
      World.enemies = World.enemies.filterNot(enemy => enemy == this)
      var pChance = Math.random()
      if (pChance < 0.1) {
        World.upgradeList =
          HealthUpgrade(location, Vec2(10, 10)) :: World.upgradeList
      } else if (pChance < 0.2) {
        World.upgradeList = newWeapon(
          location,
          Vec2(10, 10),
          World.weaponOptions(
            Random.nextInt(World.weaponOptions.length)
          )
        ) :: World.upgradeList
      } else if (pChance < 0.3) {
        World.upgradeList = SpeedUp(location, Vec2(10, 10)) :: World.upgradeList
      }
    }

    if (box.top <= 20 || box.bottom >= 492) {
      velocity = -velocity
    }
    if (box.right <= 0) {
      World.enemies = World.enemies.filterNot(enemy => enemy == this)
    }
  }

}

object Enemy {
  var Stingray: PImage = _
  def loadImages(p: PApplet): Unit = {
    Stingray = p.loadImage("src/main/Resources/Stingray.png")

  }

}
