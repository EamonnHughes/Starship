import processing.core.PApplet

case class Player(var x: Int, var y: Int) {
def draw(p: PApplet): Unit = {
  p.fill(240, 240, 240)
  p.ellipse(x-5, y, 20, 20)
  p.rect(x, y-10, 30, 20)
}
}
