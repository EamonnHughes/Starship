package starships.weapons

import processing.core.PApplet
import processing.sound.SoundFile
import starships.Starships
import starships.geom._
import starships.hostiles._
import starships.obstacles._
import starships.projectiles._
import starships.traits._
import starships.upgrades._
import starships.weapons.MissileArray.Shot
import starships.weapons._
import starships.world._

case class MissileArray(var fireRate: Int, var damage: Int) extends Weapon {

  var time: Long = System.currentTimeMillis
  def shoot(): Unit = {
    val currentTime = System.currentTimeMillis

    if (currentTime > time + fireRate && World.enemies.nonEmpty) {
      Shot.play()
      World.projectilesList = Missile(
        Vec2(World.player.location.x + 15, World.player.location.y + 15),
        World.enemies.last,
        2,
        0,
        1
      ) :: World.projectilesList
      time = currentTime
    } else if (currentTime > time + fireRate && Spawner.isBossFight) {
      Shot.play(1, 1f * Starships.volume)
      World.projectilesList = Missile(
        Vec2(World.player.location.x + 15, World.player.location.y + 15),
        World.bossList(World.currentBoss),
        2,
        0,
        1
      ) :: World.projectilesList
      time = currentTime
    } else if (currentTime > time + fireRate) {
      Shot.play(1, 1f * Starships.volume)
      World.projectilesList = Missile(
        Vec2(World.player.location.x + 15, World.player.location.y + 15),
        World.player,
        2,
        0,
        1
      ) :: World.projectilesList
      time = currentTime
    }
  }
  def special(): Unit = {}
  def drawPoints(p: PApplet): Unit = {}
}

object MissileArray {

  var Shot: SoundFile = _
  def loadSounds(p: PApplet): Unit = {
    Shot = new SoundFile(p, "src/main/Resources/Missile.mp3")
  }
}
