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
    p.rect(x, y, 20, 20)
  }
  def matchLoc: Unit = {
    if (Math.abs(World.player.y - y) > 30) {
      velocity += Math.signum(World.player.y - y) * 0.2f
    } else {
      velocity = velocity * deceleration
    }
  }
  def move: Unit = {

    y += velocity
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
