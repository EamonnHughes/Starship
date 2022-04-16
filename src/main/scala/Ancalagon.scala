import processing.core.PApplet

case class Ancalagon(var health: Int, var x: Float, var y: Float) extends Boss {

  var time: Long = System.currentTimeMillis
  def draw(p: PApplet): Unit = {
    p.fill(240, 100, 240)
    p.rect(x, y, 40, 40)
  }

  def update(): Unit = {

    val currentTime = System.currentTimeMillis
    if (currentTime > time + 900) {
      World.projectilesList =
        MachineGunProjectile(x - 25, y + 10, -1) :: World.projectilesList

      time = currentTime

    }
  }
}
