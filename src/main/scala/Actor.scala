import processing.core.PApplet

trait Actor {
  def update()
  def draw(p: PApplet)
}
