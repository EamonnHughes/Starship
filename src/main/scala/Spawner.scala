import scala.util.Random

object Spawner {
  var distance = 0
  var nextWall = Math.random() * 100
  var spawnOnTop = Random.nextBoolean()
  var length = Math.random * 3

  def checkForSpawn(): Unit = {
    if (World.enemies.length < 2) {
      spawnEnemies()
    }
  }
  def spawnEnemies(): Unit = {
    World.enemies = Enemy(1000, 256, 0, 0.9f, 3) :: World.enemies
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
          80 * length.toFloat,
          120
        ) :: World.walls
      } else {
        World.walls = Wall(
          1224 + nextWall.toFloat,
          498 - 80 * length.toFloat,
          80 * length.toFloat,
          120
        ) :: World.walls
      }
      length = Math.random * 3
      spawnOnTop = Random.nextBoolean()
    }

  }
}
