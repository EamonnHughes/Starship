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
case class Precursor(
    var location: Vec2,
    var velocity: Float,
    var deceleration: Float,
    var health: Int,
    var enemyQuantity: Float
) extends Scrolling
    with Enemy {

  def box: Box2 = Box2(0, 0, 30, 30)

  var time: Long = System.currentTimeMillis
  def draw(p: PApplet): Unit = {
    p.image(Precursor.Shuttle, location.x, location.y, 30, 30)
  }

  var goingUp = false
  def update(timeFactor: Float): Unit = {
    move(timeFactor)
    checkForCollision
  }

  def clamp(value: Float, max: Float) = {
    if (value > max) max
    else if (value < -max) -max
    else value
  }
  def move(timeFactor: Float): Unit = {
    if (!goingUp && box.bottom + location.y < 487) {
      location = location.addY(1.3f * timeFactor)
    } else if (!goingUp && box.bottom + location.y >= 487) {
      goingUp = true
    }
    if (goingUp && location.y > 25) {
      location = location.addY(-1.3f * timeFactor)
    } else if (goingUp && location.y <= 25) {
      goingUp = false
    }
    location = location.addX(-5)

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

        if (health <= 0) Starships.score += 2

      }
    }
    if (health <= 0) {

      World.enemies = World.enemies.filterNot(enemy => enemy == this)
      var pChance = Math.random()
      if (pChance < 0.04) {
        World.upgradeList = HealthUpgrade(location) :: World.upgradeList
      } else if (pChance < 0.08) {
        World.upgradeList = newWeapon(
          location,
          World.weaponOptions(
            Random.nextInt(World.weaponOptions.length)
          )
        ) :: World.upgradeList
      } else if (pChance < 0.12) {
        World.upgradeList = SpeedUp(location) :: World.upgradeList
      }

      World.explosions = Explosion(location.copy(), 0, 3) :: World.explosions
    }
    if (
      World.walls.exists(wall =>
        new Box2(
          location.x + box.left,
          location.y + box.top,
          box.width,
          box.height
        )
          .intersects(
            new Box2(
              wall.location.x + wall.box.left,
              wall.location.y + wall.box.top,
              wall.box.width,
              wall.box.height
            )
          )
      )
    ) {
      World.enemies = World.enemies.filterNot(enemy => enemy == this)
    }
    if (box.top + location.y <= 20 || box.bottom + location.y >= 492) {
      velocity = -velocity
    }
    if (box.right + location.x <= 0) {
      World.enemies = World.enemies.filterNot(enemy => enemy == this)
    }

  }

}

object Precursor {
  var Shuttle: PImage = _
  def loadImages(p: PApplet): Unit = {
    Shuttle = p.loadImage("src/main/Resources/Shuttle.png")

  }

}
