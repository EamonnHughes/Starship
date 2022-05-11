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
case class Explosion(var locaction: Vec2, var stage: Int) extends Scrolling {
  def draw(p: PApplet): Unit = {
    if (stage <= 8) {
      p.image(BasicExplosion, locaction.x, locaction.y, 256, 16)
      stage += 1
    }
  }
}
object Explosion {
  var BasicExplosion: PImage = _
  def loadImages(p: PApplet): Unit = {

    BasicExplosion = p.loadImage("src/main/Resources/Explosion.png")

  }
}
