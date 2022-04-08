import processing.core._
import processing.event.{KeyEvent, MouseEvent}

class Starships extends PApplet {

  var time: Long = System.currentTimeMillis

  override def setup(): Unit = {
    keyRepeatEnabled = false;
  }

  override def settings(): Unit = {
    size(1024, 512)
    noSmooth()
  }

  override def draw(): Unit = {

    val currentTime = System.currentTimeMillis

    if (wPressed != sPressed) {
      if (Math.abs(World.player.velocity) <= 3)
        World.player.velocity =
          World.player.velocity + (if (wPressed) -0.1f else 0.1f)
    } else
      World.player.velocity = World.player.velocity * World.player.deceleration
    World.player.y += World.player.velocity
    if (currentTime > time + 50 && shooting) {
      World.projectilesList =
        Projectile(World.player.x, World.player.y) :: World.projectilesList

      time = currentTime
    }

    background(10, 10, 10)
    World.player.draw(this)
    World.projectilesList.foreach(p => p.draw(this))
    World.walls.foreach(wall => wall.draw(this))
    World.projectilesList.foreach(p => p.shootForward())

  }

  var wPressed = false
  var sPressed = false
  var shooting = false

  override def keyPressed(event: KeyEvent): Unit = {

    if (event.getKey == 'w') {
      wPressed = true
    } else if (event.getKey == 's') {
      sPressed = true
    }
    if (event.getKey == 'q') {
      shooting = true
    }

  }

  override def keyReleased(event: KeyEvent): Unit = {

    if (event.getKey == 'w') {
      wPressed = false
    } else if (event.getKey == 's') {
      sPressed = false
    } else if (event.getKey == 'q') {
      shooting = false
    }
  }
}

object Starships extends App {
  PApplet.main(classOf[Starships])

}
