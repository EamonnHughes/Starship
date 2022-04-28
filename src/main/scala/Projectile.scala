import processing.core.PApplet

trait Projectile extends Actor {

  var direction: Int
  def shootForward(timeFactor: Float): Unit
}
