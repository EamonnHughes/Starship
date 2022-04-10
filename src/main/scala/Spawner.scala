object Spawner {
  var distance = 0
  var nextWall = Math.random() * 100

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
        .forall(wall => wall.rightX < 1024)
    ) {
      nextWall = Math.random() * 100
      World.walls = Wall(1424 + nextWall.toFloat, 20, 80, 120) :: World.walls
    }

  }
}
