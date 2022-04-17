case class Vec2(var x: Float, var y: Float) {

  def +(vec: Vec2) = Vec2(x + vec.x, y + vec.y)

  def add(dx: Float, dy: Float) = Vec2(x + dx, y + dy)
  def addX(dx: Float) = Vec2(x + dx, y)
  def addY(dy: Float) = Vec2(x, y + dy)
  def set(nx: Float, ny: Float) = Vec2(nx, ny)

  def setX(nx: Float) = Vec2(nx, y)

  def setY(ny: Float) = Vec2(x, ny)
}

case class Box2(left: Float, top: Float, width: Float, height: Float) {
  def right: Float = left + width
  def bottom: Float = top + height

  def intersects(box: Box2): Boolean = {
    left < box.right && left >= box.left && top < box.bottom && top >= box.top
  }
}

object Box2 {
  def apply(loc: Vec2, size: Vec2): Box2 = Box2(loc.x, loc.y, size.x, size.y)
}
