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
  var enemyQuantity: Float
  def box: Box2
  def update(timeFactor: Float): Unit

  def draw(p: PApplet)
}
