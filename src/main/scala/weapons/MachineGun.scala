package weapons

import processing.core.PApplet
import geom._
import hostiles._
import obstacles._
import projectiles._
import traits._
import upgrades._
import weapons._
import world._

case class MachineGun(var fireRate: Int, var damage: Int, var overHeat: Float)
    extends Weapon {

  var time: Long = System.currentTimeMillis
  def shoot(): Unit = {
    val currentTime = System.currentTimeMillis

    if (currentTime > time + fireRate && overHeat < 40) {
      World.projectilesList = MachineGunProjectile(
        Vec2(World.player.location.x + 25, World.player.location.y + 10),
        Vec2(10, 10),
        1
      ) :: World.projectilesList
      overHeat += 1
      time = currentTime
    }
  }
  def special(): Unit = {
    if (!Controls.shooting && overHeat > 0) {
      overHeat -= 0.1f
    }
  }
  def drawPoints(p: PApplet): Unit = {
    p.fill(255, 0, 0)
    p.rect(120, 0, 4 * overHeat, 20)
  }
}
