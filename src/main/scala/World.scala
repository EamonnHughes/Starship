object World {
  var player = Player(64, 256, 0, 0.9f, 3, MachineGun(50, 1, 0))
  var projectilesList = List.empty[Projectile]
  var walls = List.empty[Wall]
  var worldBorder = Border()
  var enemies = List.empty[Enemy]
  var bossList = List(Ancalagon(20, 900, 236))
  var currentBoss = 0
  def everything: List[Actor] = {
    player :: projectilesList ::: enemies
  }
  var selectWeapon = 0
  var weaponList = List(MachineGun(50, 1, 0), MissileArray(500, 3))
}
