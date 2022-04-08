import processing.core.PApplet

case class Player(
    var x: Float,
    var y: Float,
    var velocity: Float,
    var deceleration: Float,
    var lives: Int
) {
  var time: Long = System.currentTimeMillis
  def draw(p: PApplet): Unit = {
    p.fill(240, 240, 240)
    p.ellipse(x, y, 20, 20)
  }
  def checkForCollision(): Unit = {
    if (
      World.walls.exists(wall =>
        x < wall.x + wall.dimensionX && x >= wall.x && y < wall.y + wall.dimensionY && y >= wall.y
      ) || y < 20 || y > 492
    ) {
      lives -= 1
      y = 256
      velocity = 0
    }
    if (lives <= 0) {
      println("YOU DIED!")
      System.exit(0)
    }
  }
  def moving(wPressed: Boolean, sPressed: Boolean): Unit = {
    if (wPressed != sPressed) {
      if (Math.abs(velocity) <= 3)
        velocity = velocity + (if (wPressed) -0.2f else 0.2f)
    } else
      velocity = velocity * deceleration
    y += velocity
  }
  def shooting(shooting: Boolean): Unit = {
    val currentTime = System.currentTimeMillis

    if (currentTime > time + 50 && shooting) {
      World.projectilesList = Projectile(x, y) :: World.projectilesList

      time = currentTime
    }
  }
}
