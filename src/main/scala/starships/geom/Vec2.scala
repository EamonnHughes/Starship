package starships.geom

case class Vec2(var x: Float, var y: Float) {

  def +(vec: Vec2) = Vec2(x + vec.x, y + vec.y)

  def add(dx: Float, dy: Float) = Vec2(x + dx, y + dy)
  def addX(dx: Float) = Vec2(x + dx, y)
  def addY(dy: Float) = Vec2(x, y + dy)
  def set(nx: Float, ny: Float) = Vec2(nx, ny)

  def setX(nx: Float) = Vec2(nx, y)

  def setY(ny: Float) = Vec2(x, ny)
}
