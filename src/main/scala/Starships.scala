import Starships.score
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

    background(10, 10, 10)
    World.worldBorder.draw(this)
    World.walls.foreach(wall => wall.draw(this))
    World.walls.foreach(wall => wall.checkForEnd())
    World.everything.foreach(actor => actor.draw(this))
    World.everything.foreach(actor => actor.update())
    World.weaponList.foreach(weapon => weapon.special())

    drawUI(this)
    World.player.primary.drawPoints(this)
    scroll
    fill(255, 255, 255)
    text(score, 999, 20)
    Spawner.checkForSpawn()
    Spawner.spawnWalls()
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
    Spawner.distance += 1
  }

  override def keyPressed(event: KeyEvent): Unit = {

    if (event.getKey == 'w') {
      Controls.wPressed = true
    }
    if (event.getKey == 'q') {
      World.selectWeapon = (World.selectWeapon + 1) % World.weaponList.length
    }
    if (event.getKey == 's') {
      Controls.sPressed = true
    }
    if (event.getKey == ' ') {
      Controls.shooting = true
    }

  }

  override def keyReleased(event: KeyEvent): Unit = {

    if (event.getKey == 'w') {
      Controls.wPressed = false
    } else if (event.getKey == 's') {
      Controls.sPressed = false
    } else if (event.getKey == ' ') {
      Controls.shooting = false
    }
  }
  def drawUI(p: PApplet): Unit = {
    p.fill(255, 255, 255)
    p.rect(0, 0, 100, 20)
    for (i <- 0 until World.player.lives) {
      p.fill(255, 255, 0)
      p.rect(40 * i, 0, 20, 20)
    }
  }
}

object Starships extends App {

  var score = 0
  PApplet.main(classOf[Starships])

}
