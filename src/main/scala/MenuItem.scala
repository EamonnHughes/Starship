case class MenuItem(location: Vec2, size: Vec2, text: String) {
  def box: Box2 = Box2(location, size)
  def isMouseOn(mouseBox: Box2): Boolean = {
    if (mouseBox.intersects(box)) true
    else false
  }
}
