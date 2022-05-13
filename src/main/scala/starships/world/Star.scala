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
case class Star(
    location: Vec2,
    distance: Float
) {
  var scope = (55 + Math.random * distance * 50).toInt
  val r = scope - (Math.random() * 30).toInt

  val b = scope - (Math.random() * 30).toInt
  val g: Int = Math.max(r, g)
  val size = distance / 4
  def draw(p: PApplet): Unit = {
    p.fill(r, g, b)
    p.noStroke()
    p.rect(location.x, location.y, size, size)
  }
  def move: Unit = {
    location.x -= distance / 3
    if (location.x <= 0) {
      location.x = 1024
      location.y = (math.random() * 512).toFloat
    }
  }
}
