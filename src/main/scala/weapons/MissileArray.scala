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

case class MissileArray(var fireRate: Int, var damage: Int) extends Weapon {

  var time: Long = System.currentTimeMillis
  def shoot(): Unit = {
    val currentTime = System.currentTimeMillis

    if (currentTime > time + fireRate && World.enemies.nonEmpty) {
      World.projectilesList = Missile(
        Vec2(World.player.location.x + 15, World.player.location.y + 15),
        Vec2(10, 10),
        World.enemies.last,
        0,
        0,
        1
      ) :: World.projectilesList
      time = currentTime
    } else if (currentTime > time + fireRate && Spawner.isBossFight) {
      World.projectilesList = Missile(
        Vec2(World.player.location.x + 15, World.player.location.y + 15),
        Vec2(10, 10),
        World.bossList(World.currentBoss),
        0,
        0,
        1
      ) :: World.projectilesList
      time = currentTime
    } else if (currentTime > time + fireRate) {
      World.projectilesList = Missile(
        Vec2(World.player.location.x + 15, World.player.location.y + 15),
        Vec2(10, 10),
        World.player,
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
