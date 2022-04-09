import processing.core.PApplet

case class Enemy(
    var x: Float,
    var y: Float,
    var velocity: Float,
    var deceleration: Float,
    var health: Int
) extends Scrolling {
  def draw(p: PApplet): Unit = {
    p.fill(75, 175, 25)
    p.ellipse(x, y, 20, 20)
  }
  def matchLoc: Unit = {
    velocity += Math.signum(World.player.y - y) * 0.2f
  }
  def move: Unit = {

    y += velocity
  }

}
