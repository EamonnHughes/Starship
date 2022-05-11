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
case class Explosion(loc: Vec2, stage: Int) extends Actor with Scrolling {
  def draw(p: PApplet): Unit = {
    if (stage <= 8) {
      p.image(BasicExplosion, loc.x, loc.y, 256, 16)
    }
  }
}
object Explosion {
  var BasicExplosion: PImage = _
  def loadImages(p: PApplet): Unit = {

    BasicExplosion = p.loadImage("src/main/Resources/Explosion.png")

  }
}
