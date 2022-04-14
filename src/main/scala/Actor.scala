import processing.core.PApplet

trait Actor {
  var x: Float
  var y: Float
  def update()
  def draw(p: PApplet)
}
