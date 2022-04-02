import processing.core.PApplet

case class Player() {
def draw(p: PApplet): Unit = {
  p.fill(240, 240, 240)
  p.rect(100, 100, 20, 20)
}
}
