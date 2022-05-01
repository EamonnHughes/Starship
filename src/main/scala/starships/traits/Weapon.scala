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

trait Weapon {
  var fireRate: Int
  var damage: Int
  def shoot(): Unit
  def special(): Unit
  def drawPoints(p: PApplet): Unit
}
