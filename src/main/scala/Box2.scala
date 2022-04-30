case class Box2(left: Float, top: Float, width: Float, height: Float) {
  def right: Float = left + width
  def bottom: Float = top + height

  def intersects(box: Box2): Boolean = {
    left < box.right && right >= box.left && top < box.bottom && bottom >= box.top
  }
}

object Box2 {
  def apply(loc: Vec2, size: Vec2): Box2 = Box2(loc.x, loc.y, size.x, size.y)
}
