package starships

import processing.core._
import processing.event._
import starships.Starships.{missionsLoaded, state}
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
    Loading.loadstuff(this)
  }

  override def settings(): Unit = {
    size(1024, 512)

  }
  def mainMenu: Unit = {
    World.reset

    background(105, 105, 105)
    text("SPACESHIP GAME", 424, 100)
    if (mouseBox.intersects(begin.box)) {
      begin.lightness = 0.6f
    } else {
      begin.lightness = 1f
    }

    begin.draw(this)
    if (begin.isMouseOn(mouseBox)) {
      Starships.state = GameState.Selection
    }
    if (mouseBox.intersects(mExit.box)) {
      mExit.lightness = 0.6f
    } else {
      mExit.lightness = 1f
    }
    mExit.draw(this)
    if (mExit.isMouseOn(mouseBox)) {
      System.exit(0)
    }
    mute = world.MenuItem(Vec2(424, 460), Vec2(100, 30), "Mute", 1)
    if (mute.isMouseOn(mouseBox)) {
      if (Starships.volume != 0) {
        Starships.volume = 0
      } else {
        Starships.volume = 1
      }
    }

    if (Starships.volume == 0 || mute.box.intersects(mouseBox)) {
      mute.lightness = 0.6f
    } else {
      mute.lightness = 1f
    }

    mute.draw(this)
  }

  def mouseBox: Box2 = Box2(Vec2(mouseX, mouseY), Vec2(1, 1))

  var resumeGame = world.MenuItem(Vec2(20, 20), Vec2(124, 20), "Resume", 1)

  var begin = MenuItem(Vec2(424, 200), Vec2(100, 50), "Begin", 1)
  var mExit = world.MenuItem(Vec2(424, 350), Vec2(100, 50), "Exit", 1)

  var exitMenu = world.MenuItem(Vec2(20, 50), Vec2(124, 20), "Exit", 1)
  var mute = world.MenuItem(Vec2(324, 20), Vec2(124, 20), "Mute", 1)
  def inMenu: Unit = {

    if (mouseBox.intersects(resumeGame.box)) {
      resumeGame.lightness = 0.6f
    } else {
      resumeGame.lightness = 1f
    }

    resumeGame.draw(this)
    if (resumeGame.isMouseOn(mouseBox)) {

      Starships.state = GameState.InGame
      time = System.currentTimeMillis
    }
    if (mouseBox.intersects(exitMenu.box)) {
      exitMenu.lightness = 0.6f
    } else {
      exitMenu.lightness = 1f
    }

    exitMenu.draw(this)
    if (exitMenu.isMouseOn(mouseBox)) {
      Starships.state = GameState.Home
    }
    mute = world.MenuItem(Vec2(494, 20), Vec2(124, 20), "Mute", 1)

    if (mute.isMouseOn(mouseBox)) {
      if (Starships.volume != 0) {
        Starships.volume = 0
      } else {
        Starships.volume = 1
      }
    }

    if (Starships.volume == 0 || mute.box.intersects(mouseBox)) {
      mute.lightness = 0.6f
    } else {
      mute.lightness = 1f
    }

    mute.draw(this)
    fill(255, 255, 255)
    rect(154, 20, 160, 20)
    fill(0, 0, 0)
    text(s"SPEED: ${Starships.scrollspeed} Gs", 159, 35)

    fill(255, 255, 255)
    rect(324, 20, 160, 20)
    fill(0, 0, 0)
    World.currentMission.foreach(mission => text(mission.name, 329, 35))
  }
  var missionButtons = List.empty[MenuItem]
  def fillInButtons: Unit = {
    missionButtons =
      for ((mission, i) <- World.missionList.zipWithIndex) yield {
        MenuItem(
          Vec2(20, (20 + (i * 40))),
          Vec2(200, 20),
          mission.name,
          1
        )
      }

  }
  def missionSelection: Unit = {
    World.missionList = World.missionList.filterNot(mission => mission.finished)
    if (!Starships.missionsLoaded) {
      fillInButtons
      Starships.missionsLoaded = true
    }
    background(255, 255, 255)
    for ((mission, i) <- missionButtons.zipWithIndex) {
      mission.draw(this)
      if (mission.isMouseOn(mouseBox)) {
        World.currentMission = Some(World.missionList(i))
        state = GameState.InGame
      }
      if (mouseBox.intersects(mission.box)) {
        mission.lightness = 0.6f
      } else {
        mission.lightness = 1f
      }
    }
  }
  def playing: Unit = {
    val currentTime = System.currentTimeMillis
    var millisPerFrame = currentTime - time
    var timeMulti = 60f * (millisPerFrame / 1000f)
    time = currentTime
    background(0, 0, 0)
    World.stars.foreach(star => star.draw(this))
    World.stars.foreach(star => star.move)
    stroke(0, 0, 0)

    Spawner.checkForSpawn()

    World.worldBorder.draw(this)
    World.explosions.foreach(exp => exp.draw(this))
    World.everything.foreach(actor => actor.draw(this))

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
    } else if (Starships.state == GameState.Selection) {
      missionSelection
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
        scrolling.location =
          scrolling.location.addX(-(Starships.scrollspeed * timeFactor))
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
  var missionsLoaded = false
  var score = 0
  var volume = 1
  PApplet.main(classOf[Starships])

}
