object Spawner {
  var distance = 0
  var nextWall = Math.random() * 800

  World.walls = Wall(nextWall.toFloat + 1024, 20, 80, 120) :: World.walls
  def checkForSpawn(): Unit = {
    if (World.enemies.length < 0) {
      spawnEnemies()
    }
  }
  def spawnEnemies(): Unit = {
    World.enemies = Enemy(1000, 256, 0, 0.9f, 3) :: World.enemies
  }
  def spawnWalls(): Unit = {
    if (
      World.walls.headOption
        .forall(wall => wall.x < 1024)
    ) {
      nextWall = Math.random() * 800
      World.walls = Wall(nextWall.toFloat, 20, 80, 120) :: World.walls
    }

  }
}
