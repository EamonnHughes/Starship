import processing.core.PApplet

trait Projectile extends Actor {
  var x: Float
  var y: Float
  def shootForward(): Unit
}
