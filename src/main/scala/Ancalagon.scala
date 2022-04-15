import processing.core.PApplet

case class Ancalagon(var health: Int, var x: Float, var y: Float) extends Boss {

  def draw(p: PApplet): Unit = {
    p.fill(240, 100, 240)
    p.rect(x, y, 40, 40)
  }

  def update(): Unit = {}
}
