import processing.core.PApplet

case class MissileArray(var fireRate: Int, var damage: Int) extends Weapon {

  var time: Long = System.currentTimeMillis
  def shoot(): Unit = {
    val currentTime = System.currentTimeMillis

    if (currentTime > time + fireRate && World.enemies.nonEmpty) {
      World.projectilesList = Missile(
        World.player.x + 25,
        World.player.y + 10,
        World.enemies.head,
        0,
        0,
        1
      ) :: World.projectilesList
      time = currentTime
    }
  }
  def special(): Unit = {}
  def drawPoints(p: PApplet): Unit = {}
}
