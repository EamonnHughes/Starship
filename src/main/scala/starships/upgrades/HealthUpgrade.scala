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

case class HealthUpgrade(var location: Vec2, var size: Vec2)
    extends Upgrade
    with Scrolling {

  def box: Box2 = Box2(location, size)
  def update(timeFactor: Float): Unit = {
    if (
      new Box2(
        World.player.location.x + World.player.box.left,
        World.player.location.y + World.player.box.top,
        box.width,
        box.height
      ).intersects(box)
    ) {
      World.player.lives = clamp(World.player.lives + 1, 3).toInt
      World.upgradeList =
        World.upgradeList.filterNot(upgrade => upgrade == this)
    }
  }
  def draw(p: PApplet): Unit = {
    p.fill(0, 244, 244)
    box.drawBox(p)
  }

  def clamp(value: Float, max: Float) = {
    if (value > max) max
    else if (value < -max) -max
    else value
  }
}
