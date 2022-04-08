import processing.core.PApplet

case class Enemy(var x: Float, var y: Float) extends Scrolling {
  def draw(p: PApplet): Unit = {
    p.fill(75, 175, 25)
    p.ellipse(x, y, 20, 20)
  }
}
