package starships.world

import processing.core.PApplet

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
    p.rect(0, 0, 1024, 20)
    p.rect(0, 492, 1024, 20)
  }
}
