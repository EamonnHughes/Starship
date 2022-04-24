import processing.core.PApplet

case class MenuItem(location: Vec2, size: Vec2, text: String) {
  def box: Box2 = Box2(location, size)
  def isMouseOn(mouseBox: Box2): Boolean = {
    if (mouseBox.intersects(box) && Controls.mousePressed) true
    else false
  }
  def draw(p: PApplet): Unit = {
    p.fill(200, 200, 200)
    p.rect(box.left, box.top, size.x, size.y)
    p.fill(0, 0, 0)
    p.text(text, box.left + 5, box.top + 25)
  }
}
