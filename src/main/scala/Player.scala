import processing.core.PApplet

case class Player(
    var x: Float,
    var y: Float,
    var velocity: Float,
    var deceleration: Float,
    var lives: Int,
    var primary: Weapon
) {
  var time: Long = System.currentTimeMillis
  def draw(p: PApplet): Unit = {
    p.fill(240, 240, 240)
    p.rect(x, y, 20, 20)
  }
  def checkForCollision(): Unit = {
    if (
      World.walls.exists(wall =>
        x < wall.x + wall.dimensionX && x + 20 >= wall.x && y < wall.y + wall.dimensionY && y + 20 >= wall.y
      ) || y < 20 || y + 20 > 492
    ) {
      lives -= 1
      y = 256
      velocity = 0
    }
    for (i <- World.projectilesList) {
      if (
        i.x - 5 < x + 20 && i.x + 5 >= x && i.y - 5 < y + 20 && i.y + 5 >= y
      ) {
        lives -= 1
        y = 256
        velocity = 0
        World.projectilesList = World.projectilesList.filterNot(p => p == i)
      }
    }
    if (lives <= 0) {
      println("YOU DIED!")
      System.exit(0)
    }
  }
  def moving(wPressed: Boolean, sPressed: Boolean): Unit = {
    if (wPressed != sPressed) {
      velocity = clamp(velocity + (if (wPressed) -0.5f else 0.5f), 5f)

    } else
      velocity = velocity * deceleration
    y += velocity
  }

  def clamp(value: Float, max: Float) = {
    if (value > max) max
    else if (value < -max) -max
    else value
  }

  def shooting(shooting: Boolean): Unit = {
    val currentTime = System.currentTimeMillis

    if (currentTime > time + primary.fireRate && shooting) {
      World.projectilesList =
        Projectile(x + 25, y + 10, 1) :: World.projectilesList

      time = currentTime
    }
  }

}
