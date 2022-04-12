import processing.core.PApplet

case class MiningLaser(var fireRate: Int, var damage: Int, var length: Int)
    extends Weapon {

  def shoot(): Unit = {
    World.pLaser = Some(Laser(World.player.x, World.player.y))
  }
  def special(): Unit = {

    if (!Controls.shooting) {
      World.pLaser = None
    }
  }
  def drawPoints(p: PApplet): Unit = {}
}
