package starships.world

import processing.core.{PApplet, PImage}
import starships.Starships
import starships.geom._
import starships.hostiles._
import starships.obstacles._
import starships.projectiles._
import starships.traits._
import starships.upgrades._
import starships.weapons._
import starships.world._

case class Border() {
  def draw(p: PApplet): Unit = {
    p.image(Border.tBorder, 0, 0, 1024, 20)
    p.image(Border.bBorder, 0, 492, 1024, 20)
  }
}

object Border {
  var tBorder: PImage = _
  var bBorder: PImage = _
  def loadImages(p: PApplet): Unit = {
    tBorder = p.loadImage("src/main/Resources/SideTop.png")

    bBorder = p.loadImage("src/main/Resources/SideBottom.png")

  }

}
