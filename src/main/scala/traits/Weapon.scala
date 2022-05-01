package traits

import processing.core.PApplet

import geom._
import hostiles._
import obstacles._
import projectiles._
import traits._
import upgrades._
import weapons._
import world._

trait Weapon {
  var fireRate: Int
  var damage: Int
  def shoot(): Unit
  def special(): Unit
  def drawPoints(p: PApplet): Unit
}
