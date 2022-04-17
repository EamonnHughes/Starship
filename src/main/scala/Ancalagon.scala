import processing.core.PApplet

case class Ancalagon(var health: Int, var location: Vec2, var size: Vec2)
    extends Boss {
  var goingUp = false
  var time: Long = System.currentTimeMillis

  def box: Box2 = Box2(location, size)
  def draw(p: PApplet): Unit = {
    p.fill(240, 100, 240)
    p.rect(location.x, location.y, size.x, size.y)
    p.fill(255, 0, 0)
    p.rect(100, 502, health * 20, 10)
  }
  def shoot: Unit = {
    val currentTime = System.currentTimeMillis
    if (currentTime > time + 400) {
      World.projectilesList = MachineGunProjectile(
        Vec2(location.x - 25, location.y + 10),
        Vec2(10, 10),
        -1
      ) :: World.projectilesList

      time = currentTime

    }
  }
  def move: Unit = {
    if (!goingUp && y + 40 < 487) {
      y += 2
    } else if (!goingUp && y + 40 >= 487) {
      goingUp = true
    }
    if (goingUp && y > 25) {
      y -= 2
    } else if (goingUp && y <= 25) {
      goingUp = false
    }
  }
  def update(): Unit = {
    shoot
    move
    checkForCollision
  }
  def checkForCollision: Unit = {
    for (i <- World.projectilesList) {
      if (
        i.x - 5 < x + 40 && i.x + 5 >= x && i.y - 5 < y + 40 && i.y + 5 >= y && i.direction == 1
      ) {
        health -= World.player.primary.damage
        World.projectilesList = World.projectilesList.filterNot(p => p == i)

        Starships.score += World.player.primary.damage
      }
    }
    if (health <= 0) {
      Spawner.isBossFight = false
      Spawner.hasFoughtBoss = true
      Starships.score += 100
    }

  }
}
