package starships.weapons

import processing.core.PApplet
import starships.Starships
import starships.geom._
import starships.hostiles._
import starships.obstacles._
import starships.projectiles._
import starships.traits._
import starships.upgrades._
import starships.weapons._
import starships.world._

case class MachineGun(var fireRate: Int, var damage: Int, var overHeat: Float)
    extends Weapon {

  var time: Long = System.currentTimeMillis
  def shoot(): Unit = {
    val currentTime = System.currentTimeMillis

    if (currentTime > time + fireRate && overHeat < 40) {
      World.projectilesList = MachineGunProjectile(
        Vec2(World.player.location.x + 27, World.player.location.y + 7),
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
