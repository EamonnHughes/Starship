import processing.core.PApplet

case class Projectile (var x: Int, var y: Int){
  def draw(p:PApplet): Unit = {
    p.fill(255, 0, 0)
    p.ellipse(x, y, 10, 10)
  }
  def shootForward(): Unit = {
    x += 10
  }
}
