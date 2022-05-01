package starships.traits

import starships.Starships
import starships.geom._
import starships.hostiles._
import starships.obstacles._
import starships.projectiles._
import starships.traits._
import starships.upgrades._
import starships.weapons._
import starships.world._

trait Projectile extends Actor {

  var direction: Int
  def shootForward(timeFactor: Float): Unit
}
