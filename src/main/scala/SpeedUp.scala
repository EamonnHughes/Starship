import processing.core.PApplet

case class SpeedUp(var location: Vec2, var size: Vec2)
    extends Upgrade
    with Scrolling {

  def box: Box2 = Box2(location, size)
  def update(): Unit = {
    if (World.player.box.intersects(box)) {
      Starships.scrollspeed = clamp(Starships.scrollspeed + 0.5f, 3f)
      World.upgradeList =
        World.upgradeList.filterNot(upgrade => upgrade == this)
    }
  }
  def draw(p: PApplet): Unit = {
    p.fill(244, 244, 0)
    p.rect(location.x, location.y, size.x, size.y)
  }

  def clamp(value: Float, max: Float) = {
    if (value > max) max
    else if (value < -max) -max
    else value
  }
}
