import scala.util.Random

object Spawner {
  var distance = 0
  var nextWall = Math.random() * 100
  var spawnOnTop = Random.nextBoolean()
  var length = Math.random * 2

  def checkForSpawn(): Unit = {
    if (World.enemies.length < 2) {
      spawnEnemies()
    }
  }
  def spawnEnemies(): Unit = {
    World.enemies = Enemy(1040, 256, 0, 0.9f, 3) :: World.enemies
  }
  def spawnWalls(): Unit = {

    if (
      World.walls.headOption
        .forall(wall => wall.rightX < 1024)
    ) {
      nextWall = Math.random() * 100
      if (spawnOnTop) {
        World.walls = Wall(
          1224 + nextWall.toFloat,
          20,
          120,
          80 * Math.ceil(length).toFloat
        ) :: World.walls
      } else {
        World.walls = Wall(
          1224 + nextWall.toFloat,
          492 - (80 * Math.ceil(length).toFloat),
          120,
          80 * Math.ceil(length).toFloat
        ) :: World.walls
      }
      length = Math.random * 2
      spawnOnTop = Random.nextBoolean()
    }

  }
}
