package starships.world

sealed trait GameState

object GameState {
  case object Menu extends GameState

  case object Home extends GameState

  case object InGame extends GameState

  case object Selection extends GameState
}
