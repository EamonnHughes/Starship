import processing.core.PApplet

case class Missile(var x: Float, var y: Float, var target: Enemy)
    extends Actor
    with Projectile {
  def draw(p: PApplet): Unit = {
    p.fill(255, 0, 0)
    p.ellipse(x, y, 10, 10)
  }
  def update(): Unit = {
    shootForward()
  }
  def shootForward(): Unit = {
    x += 6
    if (
      x > 1024 || World.walls.exists(wall =>
        x - 5 < wall.x + wall.dimensionX && x + 5 >= wall.x && y - 5 < wall.y + wall.dimensionY && y + 5 >= wall.y
      )
    ) {
      World.projectilesList =
        World.projectilesList.filterNot(projectile => projectile == this)
    }

  }
}
