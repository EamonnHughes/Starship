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
    var spawnLoc = (Math.random() * 478 + 20).toFloat
    if (
      World.enemies
        .map(enemy => enemy.enemyQuantity)
        .sum < 3 && !isBossFight && !World.walls
        .exists(wall =>
          new Box2(
            wall.location.x + wall.box.left,
            wall.location.y + wall.box.top,
            wall.box.width,
            wall.box.height
          ).intersects(Box2(Vec2(1040, 0), Vec2(40, 512)))
        ) && spawnLoc > 20 && spawnLoc + 50 < 492
    ) {

      spawnEnemies(spawnLoc)
    }
  }
  def spawnEnemies(spawnloc: Float): Unit = {
    if (Math.random() * 2 <= 1) {
      World.enemies = Precursor(
        Vec2(1040, spawnloc),
        0,
        0.9f,
        1,
        0.5f
      ) :: World.enemies
    } else if (
      World.enemies
        .map(enemy => enemy.enemyQuantity)
        .sum + 1 <= 3
    ) {
      World.enemies = Combator(
        Vec2(1040, spawnloc),
        0,
        0.9f,
        1,
        1f
      ) :: World.enemies
    }

  }
  def spawnWalls(): Unit = {

    if (
      World.walls.headOption
        .forall(wall => wall.box.right + wall.location.x < 1024)
    ) {
      nextWall = Math.random() * 100
      var wLength = 40 + Random.nextInt(4) * 20
      if (spawnOnTop) {
        World.walls = Wall(
          Vec2(1224 + nextWall.toFloat, 0),
          Vec2(80, wLength),
          0f
        ) :: World.walls
      } else {
        World.walls = Wall(
          Vec2(1124 + nextWall.toFloat, 512 - wLength),
          Vec2(80, wLength),
          0f
        ) :: World.walls
      }
      length = Math.random * 2
      spawnOnTop = Random.nextBoolean()
    }

  }
}
