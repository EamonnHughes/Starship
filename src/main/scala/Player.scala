import processing.core.PApplet

case class Player() {
def draw(p: PApplet): Unit = {
  p.fill(240, 240, 240)
  p.rect(30, 30, 20, 20)
}
}
