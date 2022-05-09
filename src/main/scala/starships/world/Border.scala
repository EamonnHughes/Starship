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
    p.fill(100, 100, 100)
    p.image(Player.tBorder, 0, 0, 1024, 20)
    p.image(Player.bBorder, 492, 1024, 20)
  }
}

object Player {
  var tBorder: PImage = _
  var bBorder: PImage = _
  def loadImages(p: PApplet): Unit = {
    tBorder = p.loadImage("src/main/Resources/SideTop.png")

    bBorder = p.loadImage("src/main/Resources/SideBottom.png")

  }

}
