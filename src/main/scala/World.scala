object World {
  var player =
    Player(Vec2(64, 256), Vec2(40, 40), 0, 0.9f, 3, MachineGun(50, 1, 0))
  var projectilesList = List.empty[Projectile]
  var upgradeList = List.empty[Upgrade]
  var walls = List.empty[Wall]
  var worldBorder = Border()
  var enemies = List.empty[Enemy]
  var bossList = List(Ancalagon(20, Vec2(900, 236), Vec2(50, 50)))
  var currentBoss = 0
  def everything: List[Actor] = {
    player :: projectilesList ::: enemies ::: upgradeList
  }
  var selectWeapon = 0
  var weaponList = List(MachineGun(50, 1, 0), MissileArray(500, 3))
}
