import processing.core.PApplet

case class Projectile(var x: Float, var y: Float, direction: Int)
    extends Actor {
  def draw(p: PApplet): Unit = {
    p.fill(255, 0, 0)
    p.ellipse(x, y, 10, 10)
  }
  def shootForward(): Unit = {
    x += 8 * direction
    if (x > 1024) {
      World.projectilesList =
        World.projectilesList.filterNot(projectile => projectile == this)
    }
  }
}
