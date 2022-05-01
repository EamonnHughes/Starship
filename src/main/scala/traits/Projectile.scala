package traits

import geom._
import hostiles._
import obstacles._
import projectiles._
import traits._
import upgrades._
import weapons._
import world._
trait Projectile extends Actor {

  var direction: Int
  def shootForward(timeFactor: Float): Unit
}
