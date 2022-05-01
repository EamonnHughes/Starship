package traits

import geom._
import hostiles._
import obstacles._
import projectiles._
import traits._
import upgrades._
import weapons._
import world._
import processing.core.PApplet

trait Actor {

  var location: Vec2
  var size: Vec2
  def box: Box2
  def update(timeFactor: Float): Unit
  def blur: Box2 = box

  def draw(p: PApplet)
}
