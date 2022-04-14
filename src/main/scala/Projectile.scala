import processing.core.PApplet

trait Projectile extends Actor {
  var x: Float
  var y: Float
  var direction: Int
  def shootForward(): Unit
}
