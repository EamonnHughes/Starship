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

case class MissileArray(var fireRate: Int, var damage: Int) extends Weapon {

  var time: Long = System.currentTimeMillis
  def shoot(): Unit = {
    val currentTime = System.currentTimeMillis

    if (currentTime > time + fireRate && World.enemies.nonEmpty) {
      World.projectilesList = Missile(
        Vec2(World.player.location.x + 15, World.player.location.y + 15),
        World.enemies.last,
        0,
        0,
        1,
        0f
      ) :: World.projectilesList
      time = currentTime
    } else if (currentTime > time + fireRate && Spawner.isBossFight) {
      World.projectilesList = Missile(
        Vec2(World.player.location.x + 15, World.player.location.y + 15),
        World.bossList(World.currentBoss),
        0,
        0,
        1,
        0f
      ) :: World.projectilesList
      time = currentTime
    } else if (currentTime > time + fireRate) {
      World.projectilesList = Missile(
        Vec2(World.player.location.x + 15, World.player.location.y + 15),
        World.player,
        0,
        0,
        1,
        0f
      ) :: World.projectilesList
      time = currentTime
    }
  }
  def special(): Unit = {}
  def drawPoints(p: PApplet): Unit = {}
}
