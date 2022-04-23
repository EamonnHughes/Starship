import processing.core.PApplet

case class PlasmaOrb(var fireRate: Int, var damage: Int) extends Weapon {

  var time: Long = System.currentTimeMillis
  def shoot(): Unit = {
    val currentTime = System.currentTimeMillis

    if (currentTime > time + fireRate) {
      World.projectilesList = EnergyOrb(
        Vec2(World.player.location.x + 25, World.player.location.y + 10),
        Vec2(20, 20),
        1
      ) :: World.projectilesList
      time = currentTime
    }
  }
  def special(): Unit = {}
  def drawPoints(p: PApplet): Unit = {}
}
