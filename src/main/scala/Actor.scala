import processing.core.PApplet

trait Actor {

  var location: Vec2
  var size: Vec2
  def box: Box2
  def update(timeFactor: Float): Unit

  def draw(p: PApplet)
}
