import processing.core.PApplet

case class Enemy(
    var x: Float,
    var y: Float,
    var velocity: Float,
    var deceleration: Float,
    var health: Int
) extends Scrolling
    with Actor {

  var time: Long = System.currentTimeMillis
  def draw(p: PApplet): Unit = {
    p.fill(75, 175, 25)
    p.rect(x, y, 20, 20)
  }
  def update(): Unit = {
    move
    matchLoc
    checkForCollision
  }
  def matchLoc: Unit = {

    val currentTime = System.currentTimeMillis
    if (Math.abs(World.player.y - y) > 30) {
      velocity += clamp(Math.signum(World.player.y - y) * 0.2f, 3f)
    } else {
      velocity = velocity * deceleration
    }

    if (currentTime > time + 900) {
      World.projectilesList =
        Projectile(x - 25, y + 10, -1) :: World.projectilesList

      time = currentTime

    }
  }
  def clamp(value: Float, max: Float) = {
    if (value > max) max
    else if (value < -max) -max
    else value
  }
  def move: Unit = {
    y += velocity

  }
  def checkForCollision: Unit = {
    for (i <- World.projectilesList) {
      if (
        i.x - 5 < x + 20 && i.x + 5 >= x && i.y - 5 < y + 20 && i.y + 5 >= y
      ) {
        health -= World.player.primary.damage
        World.projectilesList = World.projectilesList.filterNot(p => p == i)

        Starships.score += 1
      }
    }
    if (health <= 0) {
      World.enemies = World.enemies.filterNot(enemy => enemy == this)
    }
    if (y <= 20 || y >= 492) {
      velocity = -velocity
      y += velocity * 5
    }
    if (x <= 0) {
      World.enemies = World.enemies.filterNot(enemy => enemy == this)
    }
  }

}
