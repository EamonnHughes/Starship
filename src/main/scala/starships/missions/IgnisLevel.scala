package starships.missions
import starships.traits._
case class IgnisLevel(var name: String = "Ignition", var enemies: List[Enemy])
    extends Mission {}
