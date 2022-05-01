package starships.traits

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

trait Actor {

  var location: Vec2
  var size: Vec2
  def box: Box2
  def update(timeFactor: Float): Unit
  def blur: Box2 = box

  def draw(p: PApplet)
}
