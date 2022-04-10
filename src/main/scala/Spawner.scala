object Spawner {
  def checkForSpawn(): Unit = {
    if (World.enemies.length < 3) {
      spawnEnemies()
    }
  }
  def spawnEnemies(): Unit = {
    World.enemies = Enemy(1000, 256, 0, 0.9f, 3) :: World.enemies
  }
  def spawnWalls(): Unit = {}
}
