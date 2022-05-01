package starships.world

import starships.Starships
import starships.geom._
import starships.hostiles._
import starships.obstacles._
import starships.projectiles._
import starships.traits._
import starships.upgrades._
import starships.weapons._
import starships.world._

import scala.util.Random

object Spawner {
  var nextWall = Math.random() * 200
  var spawnOnTop = Random.nextBoolean()
  var length = Math.random * 2
  var isBossFight = false
  var hasFoughtBoss = false

  def checkForSpawn(): Unit = {
    if (
      World.enemies.length < 2 && !isBossFight && !World.walls
        .exists(wall => wall.box.intersects(Box2(Vec2(1040, 0), Vec2(40, 512))))
    ) {
      spawnEnemies()
    }
  }
  def spawnEnemies(): Unit = {
    World.enemies = Enemy(
      Vec2(1040, (Math.random() * 478 + 20).toFloat),
      Vec2(40, 40),
      0,
      0.9f,
      3
    ) :: World.enemies
  }
  def spawnWalls(): Unit = {

    if (World.walls.headOption.forall(wall => wall.box.right < 1024)) {
      nextWall = Math.random() * 100
      if (spawnOnTop) {
        World.walls = Wall(
          Vec2(1224 + nextWall.toFloat, 20),
          Vec2(80, 80 * length.toFloat + 40)
        ) :: World.walls
      } else {
        World.walls = obstacles.Wall(
          Vec2(1124 + nextWall.toFloat, 492 - (80 * length.toFloat + 40)),
          Vec2(80, 80 * length.toFloat + 40)
        ) :: World.walls
      }
      length = Math.random * 2
      spawnOnTop = Random.nextBoolean()
    }

  }
}
