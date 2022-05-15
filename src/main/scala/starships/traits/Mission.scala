package starships.traits

import starships.world.Spawner.EnemyFactory

trait Mission {
  var name: String
  var enemies: List[EnemyFactory]
}
