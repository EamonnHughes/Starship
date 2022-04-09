import processing.core.PApplet

case class Enemy(
    var x: Float,
    var y: Float,
    var velocity: Float,
    var deceleration: Float,
    var health: Int
) extends Scrolling {

  var time: Long = System.currentTimeMillis
  def draw(p: PApplet): Unit = {
    p.fill(75, 175, 25)
    p.rect(x, y, 20, 20)
  }
  def matchLoc: Unit = {

    val currentTime = System.currentTimeMillis
    if (Math.abs(World.player.y - y) > 30) {
      velocity += clamp(Math.signum(World.player.y - y) * 0.3f, 4f)
    } else {
      velocity = velocity * deceleration
      if (currentTime > time + 400) {
        World.projectilesList =
          Projectile(x - 25, y + 10, -1) :: World.projectilesList

        time = currentTime

      }
    }
  }
  def clamp(value: Float, max: Float) = {
    if (value > max) max
    else if (value < -max) -max
    else value
  }
  def move: Unit = {
    if (!World.enemies.exists(enemy => enemy.y == y + velocity)) {
      y += velocity
    } else velocity = velocity * deceleration
  }
  def checkForCollision: Unit = {
    for (i <- World.projectilesList) {
      if (
        i.x - 5 < x + 20 && i.x + 5 >= x && i.y - 5 < y + 20 && i.y + 5 >= y
      ) {
        health -= 1
        World.projectilesList = World.projectilesList.filterNot(p => p == i)
      }
    }
    if (health <= 0) {
      World.enemies = World.enemies.filterNot(enemy => enemy == this)
    }
  }

}
