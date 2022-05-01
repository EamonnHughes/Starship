package starships.traits

import hostiles._
import obstacles._
import projectiles._
import processing.core.PApplet
import starships.geom.{Box2, Vec2}

trait Actor {

  var location: Vec2
  var size: Vec2
  def box: Box2
  def update(timeFactor: Float): Unit
  def blur: Box2 = box

  def draw(p: PApplet)
}
