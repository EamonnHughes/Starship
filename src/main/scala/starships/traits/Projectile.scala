package starships.traits

import geom._
import hostiles._
import obstacles._
import projectiles._
import traits._

trait Projectile extends Actor {

  var direction: Int
  def shootForward(timeFactor: Float): Unit
}
