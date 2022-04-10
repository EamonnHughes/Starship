import processing.core.PApplet

case class Projectile(var x: Float, var y: Float, direction: Int)
    extends Actor {
  def draw(p: PApplet): Unit = {
    p.fill(255, 0, 0)
    p.ellipse(x, y, 10, 10)
  }
  def update(): Unit = {
    shootForward()
  }
  def shootForward(): Unit = {
    x += 6 * direction
    if (x > 1024) {
      World.projectilesList =
        World.projectilesList.filterNot(projectile => projectile == this)
    }
  }
}
