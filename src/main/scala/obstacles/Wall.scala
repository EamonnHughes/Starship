package obstacles

import processing.core.PApplet

import geom._
import hostiles._
import obstacles._
import projectiles._
import traits._
import upgrades._
import weapons._
import world._

case class Wall(
    var location: Vec2,
    var size: Vec2
) extends Scrolling
    with Actor {

  def box: Box2 = Box2(location, size)
  def update(timeFactor: Float): Unit = {
    checkForEnd()
  }
  def draw(p: PApplet): Unit = {
    p.fill(100, 100, 100)
    p.rect(location.x, location.y, size.x, size.y)
  }

  def checkForEnd(): Unit = {
    if (box.right < 0) {
      World.walls = World.walls.filterNot(wall => wall == this)
    }
  }

}
