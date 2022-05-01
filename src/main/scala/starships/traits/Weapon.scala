package starships.traits

import processing.core.PApplet

import geom._
import hostiles._
import obstacles._
import projectiles._
import traits._

trait Weapon {
  var fireRate: Int
  var damage: Int
  def shoot(): Unit
  def special(): Unit
  def drawPoints(p: PApplet): Unit
}
