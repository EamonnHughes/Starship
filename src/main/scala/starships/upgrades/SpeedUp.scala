package starships.upgrades

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

case class SpeedUp(var location: Vec2, var enemyQuantity: Float)
    extends Upgrade
    with Scrolling {

  def box: Box2 = Box2(Vec2(0, 0), Vec2(10, 10))
  def update(timeFactor: Float): Unit = {
    if (
      new Box2(
        World.player.location.x + World.player.box.left,
        World.player.location.y + World.player.box.top,
        World.player.box.width,
        World.player.box.height
      ).intersects(
        new Box2(
          location.x + box.left,
          location.y + box.top,
          box.width,
          box.height
        )
      )
    ) {
      Starships.scrollspeed = clamp(Starships.scrollspeed + 0.2f, 1.8f)
      World.upgradeList =
        World.upgradeList.filterNot(upgrade => upgrade == this)
    }
  }
  def draw(p: PApplet): Unit = {
    p.fill(244, 244, 0)
    p.rect(
      location.x + box.left,
      location.y + box.top,
      box.width,
      box.height
    )
  }

  def clamp(value: Float, max: Float) = {
    if (value > max) max
    else if (value < -max) -max
    else value
  }
}
