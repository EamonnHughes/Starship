import processing.core.PApplet

case class Missile(
    var location: Vec2,
    var size: Vec2,
    var target: Actor,
    var velX: Float,
    var velY: Float,
    var direction: Int
) extends Actor
    with Projectile {

  def box: Box2 = Box2(location, size)
  def draw(p: PApplet): Unit = {
    p.fill(155, 155, 155)
    box.drawBox(p)

  }
  def update(timeFactor: Float): Unit = {
    shootForward(timeFactor)
  }
  def shootForward(timeFactor: Float): Unit = {

    if (target.location.y > box.bottom) {
      velY = clamp(velY + 0.1f, 5f)
    } else if (target.location.y < box.top) {
      velY = clamp(velY - 0.1f, 5f)
    } else {
      velY *= 0.9f
    }
    if (
      location.x > 1024 || World.walls.exists(wall => box.intersects(wall.box))
    ) {
      World.projectilesList =
        World.projectilesList.filterNot(projectile => projectile == this)
    }

    location = location.add(velX * timeFactor, velY * timeFactor)
    velX += .03f * direction.toFloat

  }
  def clamp(value: Float, max: Float) = {
    if (value > max) max
    else if (value < -max) -max
    else value
  }
}
