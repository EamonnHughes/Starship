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
    var direction: Int,
    var enemyQuantity: Float
) extends Actor
    with Projectile {

  def draw(p: PApplet): Unit = {
    p.fill(255, 0, 0, 50)

    p.fill(255, 0, 0)

    p.rect(location.x, location.y, box.width, box.height)
  }

  def box: Box2 = Box2(0, 0, 5, 5)

  def update(timeFactor: Float): Unit = {
    shootForward(timeFactor)
  }
  def shootForward(timeFactor: Float): Unit = {
    location = location.addX(direction * 6 * timeFactor)
    if (
      location.x > 1024 || World.walls.exists(wall =>
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
