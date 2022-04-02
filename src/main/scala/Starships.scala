import processing.core._
import processing.event.MouseEvent

class Starships extends PApplet{
  override def setup(): Unit = {}

  override def settings(): Unit = {
    size(1024, 512)
    noSmooth()
  }

  override def draw(): Unit = {
    background(10, 10, 10)
    World.player.draw(this)

  }

}
object Starships extends App {
  PApplet.main(classOf[Starships])

}

