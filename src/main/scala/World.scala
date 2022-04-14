object World {
  var player = Player(64, 256, 0, 0.9f, 3, MissileArray(50, 1))
  var projectilesList = List.empty[Projectile]
  var walls = List.empty[Wall]
  var worldBorder = Border()
  var enemies = List.empty[Enemy]
  def everything: List[Actor] = {
    player :: projectilesList ::: enemies
  }
}
