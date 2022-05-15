package starships.projectiles

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

case class MachineGunProjectile(
    var location: Vec2,
    var direction: Int
) extends Actor
    with Projectile {
  var state = 0

  var time: Long = System.currentTimeMillis
  def draw(p: PApplet): Unit = {

    val currentTime = System.currentTimeMillis
    var sprite = MachineGunProjectile.Projectile.get(state * 64, 0, 64, 64)
    p.image(sprite, location.x, location.y, 5, 5)
    if (currentTime > time + 20) {
      if (state < 3) {
        state += 1
      } else {
        state = 0
      }

      time = currentTime
    }
  }

  def box: Box2 = Box2(0, 0, 5, 5)

  def update(timeFactor: Float): Unit = {
    shootForward(timeFactor)
  }
  def shootForward(timeFactor: Float): Unit = {
    location = location.addX(direction * 6 * timeFactor)
    if (
      location.x > 1024 || location.x < 0 || World.walls.exists(wall =>
        new Box2(
          location.x + box.left,
          location.y + box.top,
          box.width,
          box.height
        ).intersects(
          new Box2(
            wall.location.x + wall.box.left,
            wall.location.y + wall.box.top,
            wall.box.width,
            wall.box.height
          )
        )
      )
    ) {
      World.projectilesList =
        World.projectilesList.filterNot(projectile => projectile == this)
    }

  }
}

object MachineGunProjectile {
  var Projectile: PImage = _
  def loadImages(p: PApplet): Unit = {
    Projectile = p.loadImage("src/main/Resources/MachineGunShot.png")

  }

}
