package projectiles

import geom.Box2
import processing.core.PApplet

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
