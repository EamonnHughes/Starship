import processing.core.PApplet

trait Projectile {
  var x: Float
  var y: Float
  def draw(p: PApplet): Unit
  def shootForward(): Unit
}
