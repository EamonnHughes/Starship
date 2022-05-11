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
import starships.world._
case class Explosion(loc: Vec2, stage: Int) {
  def draw(p: PApplet): Unit = {}
}
object Explosion {

  def loadImages(p: PApplet): Unit = {}
}
