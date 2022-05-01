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

case class MachineGunProjectile(
    var location: Vec2,
    var direction: Int
) extends Actor
    with Projectile {
  var prevLoc = location

  def draw(p: PApplet): Unit = {
    p.fill(255, 0, 0, 50)

    p.fill(255, 0, 0)

    p.rect(location.x, location.y, box.width, box.height)
  }

  def box: Box2 = Box2(0, 0, 10, 10)

  def update(timeFactor: Float): Unit = {
    shootForward(timeFactor)
  }
  def shootForward(timeFactor: Float): Unit = {
    prevLoc = location
    location = location.addX(direction * 6 * timeFactor)
    if (
      location.x > 1024 || World.walls.exists(wall => box.intersects(wall.box))
    ) {
      World.projectilesList =
        World.projectilesList.filterNot(projectile => projectile == this)
    }

  }
}
