package traits

import processing.core.PApplet

trait Weapon {
  var fireRate: Int
  var damage: Int
  def shoot(): Unit
  def special(): Unit
  def drawPoints(p: PApplet): Unit
}
