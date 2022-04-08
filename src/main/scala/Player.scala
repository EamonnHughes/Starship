import processing.core.PApplet

case class Player(var x: Float, var y: Float, var velocity: Float) {
  def draw(p: PApplet): Unit = {
    p.fill(240, 240, 240)
    p.ellipse(x, y, 20, 20)
  }
}
