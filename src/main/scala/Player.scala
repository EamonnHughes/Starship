import processing.core.PApplet

case class Player(
    var x: Float,
    var y: Float,
    var velocity: Float,
    var deceleration: Float
) {
  def draw(p: PApplet): Unit = {
    p.fill(240, 240, 240)
    p.ellipse(x, y, 20, 20)
  }
  def checkForCollision(): Unit = {
    if (
      World.walls.exists(wall =>
        x < wall.x + wall.dimensionX && x >= wall.x && y < wall.y + wall.dimensionY && y >= wall.y
      )
    ) {
      println("YOU DIED!")
      System.exit(0)
    }
  }
}
