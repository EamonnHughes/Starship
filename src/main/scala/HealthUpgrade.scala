import processing.core.PApplet

case class HealthUpgrade(var location: Vec2, var size: Vec2)
    extends Scrolling
    with Actor {

  def box: Box2 = Box2(location, size)
  def update(): Unit = {
    if (World.player.box.intersects(box)) {
      World.player.lives = clamp(World.player.lives + 1, 3).toInt

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
