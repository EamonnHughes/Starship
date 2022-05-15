package starships.world

import processing.core._
import processing.event._
import starships.geom._
import starships.hostiles._
import starships.obstacles._
import starships.projectiles._
import starships.traits._
import starships.upgrades._
import starships.weapons._
import starships.world.Explosion._
import starships.world.Player.Swordfish
import starships.world._
case class Explosion(var location: Vec2, var stage: Int, scale: Int)
    extends Scrolling {

  var time: Long = System.currentTimeMillis
  def draw(p: PApplet): Unit = {
    if (scale == 3) {
      if (stage <= 8) {

        val currentTime = System.currentTimeMillis
        var sprite = SmallExplosion.get((stage - 1) * 384, 0, 384, 384)

        p.image(sprite, location.x, location.y, 30, 30)

        if (currentTime > time + 50) {
          stage += 1

          time = currentTime
        }
      }
    }
    if (scale == 4) {
      if (stage <= 8) {

        val currentTime = System.currentTimeMillis
        var sprite = BasicExplosion.get((stage - 1) * 512, 0, 512, 512)

        p.image(sprite, location.x, location.y, 40, 40)

        if (currentTime > time + 50) {
          stage += 1

          time = currentTime
        }
      }
    }
    if (scale == 5) {
      if (stage <= 8) {

        val currentTime = System.currentTimeMillis
        var sprite = LargeExplosion.get((stage - 1) * 640, 0, 640, 640)

        p.image(sprite, location.x, location.y, 50, 50)

        if (currentTime > time + 50) {
          stage += 1

          time = currentTime
        }
      }
    }
  }
}
object Explosion {
  var BasicExplosion: PImage = _

  var SmallExplosion: PImage = _

  var LargeExplosion: PImage = _
  def loadImages(p: PApplet): Unit = {

    BasicExplosion = p.loadImage("src/main/Resources/Explosion.png")
    SmallExplosion = p.loadImage("src/main/Resources/ExplosionSmall.png")
    LargeExplosion = p.loadImage("src/main/Resources/ExplosionLarge.png")

  }
}
