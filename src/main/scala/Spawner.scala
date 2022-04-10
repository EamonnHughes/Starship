object Spawner {
  var distance = 0
  var nextWall = Math.random() * 50 + 800

  World.walls = Wall(nextWall.toFloat, 20, 80, 120) :: World.walls
  var lastWall = 0
  def checkForSpawn(): Unit = {
    if (World.enemies.length < 3) {
      spawnEnemies()
    }
  }
  def spawnEnemies(): Unit = {
    World.enemies = Enemy(1000, 256, 0, 0.9f, 3) :: World.enemies
  }
  def spawnWalls(): Unit = {
    if (distance == lastWall + nextWall) {
      lastWall = distance
      nextWall = Math.random() * 50 + 800
      World.walls = Wall(nextWall.toFloat, 20, 80, 120) :: World.walls
    }
  }
}
