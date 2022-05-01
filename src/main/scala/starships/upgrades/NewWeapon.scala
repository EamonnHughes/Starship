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

case class newWeapon(var location: Vec2, var weapon: Weapon)
    extends Upgrade
    with Scrolling {

  def box: Box2 = Box2(Vec2(0, 0), Vec2(10, 10))
  def update(timeFactor: Float): Unit = {
    if (
      new Box2(
        World.player.location.x + World.player.box.left,
        World.player.location.y + World.player.box.top,
        box.width,
        box.height
      ).intersects(box)
    ) {
      if (!World.weaponList.exists(w => w.getClass == weapon.getClass)) {
        World.weaponList = weapon :: World.weaponList
      } else { World.player.lives = clamp(World.player.lives + 1, 3).toInt }
      World.upgradeList =
        World.upgradeList.filterNot(upgrade => upgrade == this)

    }
  }
  def draw(p: PApplet): Unit = {
    p.fill(244, 100, 255)
    p.rect(
      World.player.location.x + box.left,
      World.player.location.y + box.top,
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
