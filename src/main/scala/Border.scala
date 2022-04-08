import processing.core.PApplet

case class Border() {
  def draw(p: PApplet): Unit = {
    p.fill(100, 100, 100)
    p.rect(0, 0, 1024, 20)
    p.rect(0, 492, 1024, 20)
  }
}
