import processing.core.PApplet

case class Ancalagon(var health: Int, var x: Float, var y: Float) extends Boss {
  var goingUp = false
  var time: Long = System.currentTimeMillis
  def draw(p: PApplet): Unit = {
    p.fill(240, 100, 240)
    p.rect(x, y, 40, 40)
  }
  def shoot: Unit = {
    val currentTime = System.currentTimeMillis
    if (currentTime > time + 400) {
      World.projectilesList =
        MachineGunProjectile(x - 25, y + 10, -1) :: World.projectilesList

      time = currentTime

    }
  }
  def move: Unit = {
    if (!goingUp && y + 40 < 487) {
      y += 5
    } else if (!goingUp && y + 40 >= 487) {
      goingUp = true
    }
    if (goingUp && y > 25) {
      y -= 5
    } else if (goingUp && y <= 25) {
      goingUp = false
    }
  }
  def update(): Unit = {
    shoot
    move
  }
  def checkForCollision: Unit = {
    for (i <- World.projectilesList) {
      if (
        i.x - 5 < x + 20 && i.x + 5 >= x && i.y - 5 < y + 20 && i.y + 5 >= y && i.direction == 1
      ) {
        health -= World.player.primary.damage
        World.projectilesList = World.projectilesList.filterNot(p => p == i)

        Starships.score += World.player.primary.damage
      }
    }
    if (health <= 0) {
      Spawner.isBossFight = false
    }

  }
}
