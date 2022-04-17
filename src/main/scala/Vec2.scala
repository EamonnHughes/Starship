case class Vec2(x: Float, y: Float) {

  def +(vec: Vec2) = Vec2(x + vec.x, y + vec.y)
}

case class Box2(x: Float, y: Float, width: Float, height: Float) {
  def right = x + width
  def bottom = y + height

  def intersects(box: Box2): Boolean = {
    x < box.right && x >= box.x && y < box.bottom && y >= box.y
  }
}
