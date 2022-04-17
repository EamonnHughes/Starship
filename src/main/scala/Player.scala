import processing.core.{PApplet, PImage}

case class Player(
    var location: Vec2,
    size: Vec2,
    var velocity: Float,
    var deceleration: Float,
    var lives: Int,
    var primary: Weapon
) extends Actor {

  def box: Box2 = Box2(location, size)
  def draw(p: PApplet): Unit = {

    p.image(Player.Swordfish, location.x, location.y, size.x, size.y)
  }

  def update(): Unit = {
    World.player.primary = World.weaponList(World.selectWeapon)
    checkForCollision()
    moving()
    if (Controls.shooting) {
      shooting()
    }
    primary.special()
  }
  def checkForCollision(): Unit = {
    if (
      World.walls.exists(wall =>
        box.intersects(wall.box)
      ) || box.top < 20 || box.bottom > 492
    ) {
      lives -= 1
      location = location.setY(256)
      velocity = 0
    }
    for (i <- World.projectilesList) {
      if (box.intersects(i.box) && i.direction == -1) {
        lives -= 1
        location = location.setY(256)
        velocity = 0
        World.projectilesList = World.projectilesList.filterNot(p => p == i)
      }
    }
    if (lives <= 0) {
      println("YOU DIED! SCORE: " + Starships.score)
      System.exit(0)
    }
  }
  def moving(): Unit = {
    if (Controls.wPressed != Controls.sPressed) {
      velocity = clamp(velocity + (if (Controls.wPressed) -0.5f else 0.5f), 5f)

    } else
      velocity = velocity * deceleration
    location.addY(velocity)
  }

  def clamp(value: Float, max: Float) = {
    if (value > max) max
    else if (value < -max) -max
    else value
  }

  def shooting(): Unit = {
    primary.shoot()
  }

}

object Player {
  var Swordfish: PImage = _
  def loadImages(p: PApplet): Unit = {
    Swordfish = p.loadImage("src/main/Resources/Swordfish.png")

  }

}
