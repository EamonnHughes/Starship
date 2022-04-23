import processing.core.PApplet

case class newWeapon(var location: Vec2, var size: Vec2)
    extends Upgrade
    with Scrolling {

  def box: Box2 = Box2(location, size)
  def update(): Unit = {
    if (World.player.box.intersects(box)) {
      World.weaponList = PlasmaOrb(10, 7) :: World.weaponList
      World.upgradeList =
        World.upgradeList.filterNot(upgrade => upgrade == this)
    }
  }
  def draw(p: PApplet): Unit = {
    p.fill(244, 100, 255)
    p.rect(location.x, location.y, size.x, size.y)
  }

  def clamp(value: Float, max: Float) = {
    if (value > max) max
    else if (value < -max) -max
    else value
  }
}
