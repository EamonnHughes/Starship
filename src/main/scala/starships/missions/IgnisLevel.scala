package starships.missions
import starships.traits._
import starships.world.Spawner.{CombatorFactory, EnemyFactory, PrecursorFactory}
case class IgnisLevel(
    var name: String = "Ignition",
    var enemies: List[EnemyFactory] = List(CombatorFactory, PrecursorFactory)
) extends Mission {}
