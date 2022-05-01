package world

import processing.core.PApplet

import geom._
import hostiles._
import obstacles._
import projectiles._
import traits._
import upgrades._
import weapons._
import world._

case class Border() {
  def draw(p: PApplet): Unit = {
    p.fill(100, 100, 100)
    p.rect(0, 0, 1024, 20)
    p.rect(0, 492, 1024, 20)
  }
}
