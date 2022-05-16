package starships.missions
import starships.traits._
import starships.world.Spawner.{CombatorFactory, EnemyFactory, PrecursorFactory}
import starships.world._
case class IgnisLevel(
    var name: String = "Ignition",
    var enemies: List[EnemyFactory] = List(CombatorFactory, PrecursorFactory),
    var finished: Boolean = false
) extends Mission {

  def load: Unit = {
    World.currentBoss = 1
  }
}
