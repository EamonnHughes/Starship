package starships

import processing.core._
import processing.event._
import starships.geom._
import starships.hostiles._
import starships.obstacles._
import starships.projectiles._
import starships.traits._
import starships.upgrades._
import starships.weapons._
import starships.world._

class Starships extends PApplet {

  var time: Long = System.currentTimeMillis

  override def setup(): Unit = {
    keyRepeatEnabled = false
    textFont(new PFont(PFont.findFont("SansSerif"), true), 16)

    Player.loadImages(this)
    Combator.loadImages(this)
    Precursor.loadImages(this)
    Ancalagon.loadImages(this)
    Missile.loadImages(this)
    EnergyOrb.loadImages(this)
    Wall.loadImages(this)
    Border.loadImages(this)
    Explosion.loadImages(this)
  }

  override def settings(): Unit = {
    size(1024, 512)

  }
  def mainMenu: Unit = {
    World.reset

    background(105, 105, 255)
    text("SPACESHIP GAME", 424, 100)
    var begin = MenuItem(Vec2(424, 200), Vec2(100, 50), "Begin", 1)

    begin.isMouseOn(mouseBox)
    begin.draw(this)
    if (begin.isMouseOn(mouseBox)) {
      Starships.state = GameState.InGame
    }
    var exit = world.MenuItem(Vec2(424, 350), Vec2(100, 50), "Exit", 1)

    exit.isMouseOn(mouseBox)
    exit.draw(this)
    if (exit.isMouseOn(mouseBox)) {
      System.exit(0)
    }
  }

  def mouseBox: Box2 = Box2(Vec2(mouseX, mouseY), Vec2(1, 1))
  def inMenu: Unit = {

    var resume = world.MenuItem(Vec2(20, 20), Vec2(124, 20), "Resume", 1)
    resume.isMouseOn(mouseBox)
    resume.draw(this)
    if (resume.isMouseOn(mouseBox)) {

      Starships.state = GameState.InGame
      time = System.currentTimeMillis
    }
    var exit = world.MenuItem(Vec2(20, 50), Vec2(124, 20), "Exit", 1)
    exit.isMouseOn(mouseBox)
    exit.draw(this)
    if (exit.isMouseOn(mouseBox)) {
      Starships.state = GameState.Home
    }
    fill(255, 255, 255)
    rect(154, 20, 160, 20)
    fill(0, 0, 0)
    text(s"SPEED: ${Starships.scrollspeed} Gs", 159, 35)

  }
  def playing: Unit = {
    val currentTime = System.currentTimeMillis
    var millisPerFrame = currentTime - time
    var timeMulti = 60f * (millisPerFrame / 1000f)
    time = currentTime
    background(10, 10, 50)

    Spawner.checkForSpawn()
    World.explosions.foreach(exp => exp.draw(this))
    World.everything.foreach(actor => actor.draw(this))

    World.worldBorder.draw(this)

    World.everything.foreach(actor => actor.update(timeMulti))

    if (Spawner.isBossFight) {
      World.bossList(World.currentBoss).draw(this)
      World.bossList(World.currentBoss).update(timeMulti)
      World.walls = World.walls.filterNot(wall =>
        wall.box.intersects(Box2(Vec2(1019, 0), Vec2(800, 512)))
      )
    }
    World.weaponList.foreach(weapon => weapon.special())
    drawUI(this)
    World.player.primary.drawPoints(this)
    scroll(timeMulti)
    if (!Spawner.isBossFight) {
      Spawner.spawnWalls()
    }
    if (Starships.score >= 100 && !Spawner.hasFoughtBoss) {
      Spawner.isBossFight = true
    }
  }

  override def draw(): Unit = {
    if (Starships.state == GameState.InGame) {
      playing
    } else if (Starships.state == GameState.Menu) {
      inMenu
    } else if (Starships.state == GameState.Home) {
      mainMenu
    }

  }

  def scroll(timeFactor: Float): Unit = {

    World.enemies.foreach({
      case scrolling: Scrolling =>
        scrolling.location =
          scrolling.location.addX(-(Starships.scrollspeed * timeFactor))
      case _ =>
    })
    World.walls.foreach({
      case scrolling: Scrolling =>
        scrolling.location =
          scrolling.location.addX(-(Starships.scrollspeed * timeFactor))
      case _ =>
    })
    World.upgradeList.foreach({
      case scrolling: Scrolling =>
        scrolling.location =
          scrolling.location.addX(-(Starships.scrollspeed * timeFactor))
      case _ =>
    })
    World.explosions.foreach({
      case scrolling: Scrolling =>
        scrolling.locaction =
          scrolling.locaction.addX(-(Starships.scrollspeed * timeFactor))
      case _ =>
    })
  }

  override def keyPressed(event: KeyEvent): Unit = {

    if (event.getKey == 'w') {
      Controls.wPressed = true
    } else if (event.getKey == 'q') {
      World.selectWeapon = (World.selectWeapon + 1) % World.weaponList.length
    } else if (event.getKey == 's') {
      Controls.sPressed = true
    } else if (event.getKey == ' ') {
      Controls.shooting = true
    }

    if (event.getKey == 27) {
      if (Starships.state == GameState.InGame) Starships.state = GameState.Menu
      key = 0
    }

  }
  override def mousePressed(event: MouseEvent): Unit = {
    def box: Box2 = Box2(Vec2(mouseX, mouseY), Vec2(1, 1))
    Controls.mousePressed = true
  }
  override def mouseReleased(event: MouseEvent): Unit = {
    Controls.mousePressed = false
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

  var state: GameState = GameState.Home

  var scrollspeed: Float = 1f

  var score = 0
  PApplet.main(classOf[Starships])

}
