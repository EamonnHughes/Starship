package upgrades

import geom._
import hostiles._
import obstacles._
import projectiles._
import traits._
import upgrades._
import weapons._
import world._
import processing.core.PApplet

case class SpeedUp(var location: Vec2, var size: Vec2)
    extends Upgrade
    with Scrolling {

  def box: Box2 = Box2(location, size)
  def update(timeFactor: Float): Unit = {
    if (World.player.box.intersects(box)) {
      Starships.scrollspeed = clamp(Starships.scrollspeed + 0.3f, 3f)
      World.upgradeList =
        World.upgradeList.filterNot(upgrade => upgrade == this)
    }
  }
  def draw(p: PApplet): Unit = {
    p.fill(244, 244, 0)
    box.drawBox(p)
  }

  def clamp(value: Float, max: Float) = {
    if (value > max) max
    else if (value < -max) -max
    else value
  }
}
