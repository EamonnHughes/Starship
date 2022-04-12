import processing.core.PApplet

case class MiningLaser(var fireRate: Int, var damage: Int, var length: Int)
    extends Weapon {

  def shoot(): Unit = {

    World.Laser = List(Laser(World.player.x, World.player.y))
  }
  def special(): Unit = {

    if (!Controls.shooting) {
      World.Laser = List.empty[Laser]
    }
  }
  def drawPoints(p: PApplet): Unit = {}
}
