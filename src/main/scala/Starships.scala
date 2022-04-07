import processing.core._
import processing.event.{KeyEvent, MouseEvent}

class Starships extends PApplet{
  override def setup(): Unit = {}

  override def settings(): Unit = {
    size(1024, 512)
    noSmooth()
  }

  override def draw(): Unit = {
    background(10, 10, 10)
    World.player.draw(this)
    World.projectilesList.foreach(p => p.draw(this))

  }

  override def keyPressed(event: KeyEvent): Unit = {
    if(event.getKey == 'w'){
      World.player.y -= 10
    } else if(event.getKey == 's'){
      World.player.y += 10
    }
    if(event.getKey == 'q'){
      World.projectilesList = Projectile(World.player.x, World.player.y) :: World.projectilesList
    }
  }

}
object Starships extends App {
  PApplet.main(classOf[Starships])

}

