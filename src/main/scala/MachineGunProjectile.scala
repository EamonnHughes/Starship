import processing.core.PApplet

case class MachineGunProjectile(
    var location: Vec2,
    var size: Vec2,
    var direction: Int
) extends Actor
    with Projectile {
  def draw(p: PApplet): Unit = {
    p.fill(255, 0, 0)
    p.ellipse(x, y, 4, 4)
  }

  def box: Box2 = Box2(location, size)
  def update(): Unit = {
    shootForward()
  }
  def shootForward(): Unit = {
    location.addX(direction * 6)
    if (x > 1024 || World.walls.exists(wall => box.intersects(wall.box))) {
      World.projectilesList =
        World.projectilesList.filterNot(projectile => projectile == this)
    }

  }
}
