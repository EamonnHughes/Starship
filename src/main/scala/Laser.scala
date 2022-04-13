import processing.core.PApplet

case class Laser(var x: Float, var y: Float) extends Actor with Projectile {

  var time: Long = System.currentTimeMillis
  def draw(p: PApplet): Unit = {
    p.fill(255, 0, 0)
    p.rect(World.player.x, World.player.y + 9, 1024 - x, 2s)
  }

  def update(): Unit = {

    val currentTime = System.currentTimeMillis
    if (currentTime > time + 100) {
      //World.enemies.foreach()

      println("LASER!")
      time = currentTime
    }
  }
}
