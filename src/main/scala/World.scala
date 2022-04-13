object World {
  var player = Player(64, 256, 0, 0.9f, 3, MiningLaser(50, 1, 0))
  var projectilesList = List.empty[MachineGunProjectile]
  var walls = List.empty[Wall]
  var worldBorder = Border()
  var enemies = List.empty[Enemy]
  var pLaser = Option.empty[Laser]
  def everything: List[Actor] = {
    player :: projectilesList ::: pLaser.toList ::: enemies
  }
}
