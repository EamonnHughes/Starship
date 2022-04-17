import scala.util.Random

object Spawner {
  var distance = 0
  var nextWall = Math.random() * 100
  var spawnOnTop = Random.nextBoolean()
  var length = Math.random * 2
  var isBossFight = false
  var hasFoughtBoss = false

  def checkForSpawn(): Unit = {
    if (World.enemies.length < 2 && !isBossFight) {
      spawnEnemies()
    }
  }
  def spawnEnemies(): Unit = {
    World.enemies =
      Enemy(Vec2(1040, 256), Vec2(20, 20), 0, 0.9f, 3) :: World.enemies
  }
  def spawnWalls(): Unit = {

    if (World.walls.headOption.forall(wall => wall.box.right < 1024)) {
      nextWall = Math.random() * 100
      if (spawnOnTop) {
        World.walls = Wall(
          Vec2(1224 + nextWall.toFloat, 20),
          Vec2(20, 80 * Math.ceil(length).toFloat)
        ) :: World.walls
      } else {
        World.walls = Wall(
          Vec2(1224 + nextWall.toFloat, 492 - 80 * Math.ceil(length).toFloat),
          Vec2(20, 80 * Math.ceil(length).toFloat)
        ) :: World.walls
      }
      length = Math.random * 2
      spawnOnTop = Random.nextBoolean()
    }

  }
}
