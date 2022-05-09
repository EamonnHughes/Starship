package starships.obstacles

import processing.core.{PApplet, PImage}
import starships.Starships
import starships.geom._
import starships.hostiles._
import starships.obstacles.Wall.WallBody
import starships.obstacles._
import starships.projectiles._
import starships.traits._
import starships.upgrades._
import starships.weapons._
import starships.world._

case class Wall(
    var location: Vec2,
    var size: Vec2,
    var enemyQuantity: Float
) extends Scrolling
    with Actor {

  def box: Box2 = Box2(Vec2(0, 0), size)
  def update(timeFactor: Float): Unit = {
    checkForEnd()
  }
  def draw(p: PApplet): Unit = {
    p.fill(100, 100, 100)
    for (i <- 0 until size.y.toInt by 10) {
      p.image(WallBody, location.x, location.y + i, 80, 10)
    }
  }

  def checkForEnd(): Unit = {
    if (box.right < 0) {
      World.walls = World.walls.filterNot(wall => wall == this)
    }
  }

}

object Wall {
  var WallBody: PImage = _

  var WallTop: PImage = _

  var WallBottom: PImage = _
  def loadImages(p: PApplet): Unit = {
    WallBody = p.loadImage("src/main/Resources/WallBody.png")
    WallTop = p.loadImage("src/main/Resources/WallTop.png")
    WallBottom = p.loadImage("src/main/Resources/WallBottom.png")

  }

}
