import Starships.score
import processing.core._
import processing.event.{KeyEvent, MouseEvent}
import processing.awt.PGraphicsJava2D
import processing.opengl.PGraphicsOpenGL
class Starships extends PApplet {

  override def setup(): Unit = {
    keyRepeatEnabled = false;

    Player.loadImages(this)
  }

  override def settings(): Unit = {
    size(1024, 512)
    noSmooth()
  }
  def mainMenu: Unit = {
    World.reset

    background(255, 255, 255)
    fill(0, 0, 0)
    text("PRESS X TO BEGIN", 500, 250)
  }
  def inMenu: Unit = {

    background(255, 255, 255)
    fill(0, 0, 0)
    text("IN PAUSE MENU", 500, 250)

    text("PRESS X TO RESUME", 500, 350)
  }
  def playing: Unit = {
    background(10, 10, 10)
    World.worldBorder.draw(this)
    World.walls.foreach(wall => wall.draw(this))
    World.walls.foreach(wall => wall.checkForEnd())
    World.everything.foreach(actor => actor.draw(this))
    World.everything.foreach(actor => actor.update())
    if (Spawner.isBossFight) {
      World.bossList(World.currentBoss).draw(this)
      World.bossList(World.currentBoss).update()
    }
    World.weaponList.foreach(weapon => weapon.special())
    drawUI(this)
    World.player.primary.drawPoints(this)
    scroll
    fill(255, 255, 255)
    text(score, 999, 20)
    Spawner.checkForSpawn()
    if (!Spawner.isBossFight) {
      Spawner.spawnWalls()
    }
    if (score >= 100 && !Spawner.hasFoughtBoss) {
      Spawner.isBossFight = true
    }
  }

  override def draw(): Unit = {
    if (Starships.state == "Playing") {
      playing
    } else if (Starships.state == "Menu") {
      inMenu
    } else if (Starships.state == "Home") {
      mainMenu
    }

  }

  def scroll: Unit = {
    World.walls.foreach({
      case scrolling: Scrolling =>
        scrolling.location.x -= 1
      case _ =>
    })
    World.upgradeList.foreach({
      case scrolling: Scrolling =>
        scrolling.location.x -= 1
      case _ =>
    })
    World.enemies.foreach({
      case scrolling: Scrolling =>
        scrolling.location.x -= 1
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
    if (event.getKey == 'x' && Starships.state == "Home") {
      Starships.state = "Playing"
    } else if (event.getKey == 'x' && Starships.state == "Menu") {
      Starships.state = "Playing"
    } else if (event.getKey == 'x') {
      Starships.state = "Menu"
      Starships.state = "Menu"
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

  var state = "Home"

  var score = 0
  PApplet.main(classOf[Starships])

}
