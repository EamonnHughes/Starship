import processing.core._
import processing.event.{KeyEvent, MouseEvent}

class Starships extends PApplet {

  override def setup(): Unit = {
    keyRepeatEnabled = false;
  }

  override def settings(): Unit = {
    size(1024, 512)
    noSmooth()
  }

  override def draw(): Unit = {

    World.player.moving(wPressed, sPressed)

    World.player.shooting(shooting)
    background(10, 10, 10)
    World.worldBorder.draw(this)
    World.player.draw(this)
    World.enemies.foreach(enemy => enemy.draw(this))
    World.projectilesList.foreach(p => p.draw(this))
    World.walls.foreach(wall => wall.draw(this))
    drawUI(this)
    World.projectilesList.foreach(p => p.shootForward())
    scroll
    World.player.checkForCollision()
  }
  def scroll: Unit = {
    World.walls.foreach({
      case scrolling: Scrolling =>
        scrolling.x -= 1
      case _ =>
    })
    World.enemies.foreach({
      case scrolling: Scrolling =>
        scrolling.x -= 1
      case _ =>
    })
  }

  var wPressed = false
  var sPressed = false
  var shooting = false

  override def keyPressed(event: KeyEvent): Unit = {

    if (event.getKey == 'w') {
      wPressed = true
    }
    if (event.getKey == 's') {
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
  def drawUI(p: PApplet): Unit = {
    for (i <- 0 until World.player.lives) {
      p.fill(255, 255, 0)
      p.rect(40 * i, 20, 20, 0)
    }
  }
}

object Starships extends App {
  PApplet.main(classOf[Starships])

}
