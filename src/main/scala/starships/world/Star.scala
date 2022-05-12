package starships.world

import processing.core._
import processing.event._
import starships.geom._
import starships.hostiles._
import starships.obstacles._
import starships.projectiles._
import starships.traits._
import starships.upgrades._
import starships.weapons._
import starships.world._
case class Star(location: Vec2, velocity: Float) {
  def draw(p: PApplet): Unit = {
    p.fill(255, 255, 255)
    p.noStroke()
    p.rect(location.x, location.y, 2, 2)
  }
}
