import processing.core.PApplet

case class Projectile (var x: Int, var y: Int){
  def draw(p:PApplet): Unit = {
    p.fill(255, 0, 0)
    p.ellipse(x-5, y-5, 10, 10)
  }
}
