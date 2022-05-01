package starships.projectiles

import processing.core.PApplet
import starships.Starships
import starships.geom._
import starships.hostiles._
import starships.obstacles._
import starships.projectiles._
import starships.traits._
import starships.upgrades._
import starships.weapons._
import starships.world._

case class EnergyOrb(
    var location: Vec2,
    var size: Vec2,
    var direction: Int
) extends Actor
    with Projectile {
  def draw(p: PApplet): Unit = {
    p.fill(255, 255, 0, 50)
    p.ellipse(
      location.x + (size.x * .5).toFloat,
      location.y + (size.y * .5).toFloat,
      size.x,
      size.y
    )
  }

  def box: Box2 = Box2(location, size)
  def update(timeFactor: Float): Unit = {
    shootForward(timeFactor)
  }
  def shootForward(timeFactor: Float): Unit = {
    location = location.addX(direction * 3 * timeFactor)
    if (location.x > 1024) {
      World.projectilesList =
        World.projectilesList.filterNot(projectile => projectile == this)
    }

  }
}
