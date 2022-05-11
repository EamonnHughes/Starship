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
import starships.world.Explosion.BasicExplosion
import starships.world.Player.Swordfish
import starships.world._
case class Explosion(var location: Vec2, var stage: Int) extends Scrolling {

  var time: Long = System.currentTimeMillis
  def draw(p: PApplet): Unit = {
    if (stage <= 8) {

      val currentTime = System.currentTimeMillis
      var sprite = BasicExplosion.get((stage - 1) * 512, 0, 512, 512)

      p.image(sprite, location.x, location.y, 16, 16)

      if (currentTime > time + 3000) {
        stage += 1
      }
    }
  }
}
object Explosion {
  var BasicExplosion: PImage = _
  def loadImages(p: PApplet): Unit = {

    BasicExplosion = p.loadImage("src/main/Resources/Explosion.png")

  }
}
