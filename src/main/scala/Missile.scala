import processing.core.PApplet

case class Missile(
    var x: Float,
    var y: Float,
    var target: Enemy,
    var velX: Float,
    var velY: Float
) extends Actor
    with Projectile {
  def draw(p: PApplet): Unit = {
    p.fill(90, 90, 90)
    p.ellipse(x, y, 10, 10)

    p.fill(255, 0, 0)
    p.ellipse(x + 1, y + 1, 2, 2)
  }
  def update(): Unit = {
    shootForward()
    x += velX
    y += velY
    velX += 0.06f
  }
  def shootForward(): Unit = {

    if (target.y > y + 10) {
      velY = clamp(velY + 0.1f, 5f)
    } else if (target.y < y) {
      velY = clamp(velY - 0.1f, 5f)
    } else {
      velY *= 0.9f
    }
    if (
      x > 1024 || World.walls.exists(wall =>
        x - 5 < wall.x + wall.dimensionX && x + 5 >= wall.x && y - 5 < wall.y + wall.dimensionY && y + 5 >= wall.y
      )
    ) {
      World.projectilesList =
        World.projectilesList.filterNot(projectile => projectile == this)
    }

  }
  def clamp(value: Float, max: Float) = {
    if (value > max) max
    else if (value < -max) -max
    else value
  }
}
