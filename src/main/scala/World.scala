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
  var weaponList: List[Weapon] =
    List(MachineGun(50, 1, 0), MissileArray(500, 3))
  def reset: Unit = {
    player =
      Player(Vec2(64, 256), Vec2(40, 40), 0, 0.9f, 3, MachineGun(50, 1, 0))
    projectilesList = List.empty[Projectile]
    upgradeList = List.empty[Upgrade]
    walls = List.empty[Wall]
    worldBorder = Border()
    enemies = List.empty[Enemy]
    bossList = List(Ancalagon(20, Vec2(900, 236), Vec2(50, 50)))
    currentBoss = 0
    weaponList = List(MachineGun(50, 1, 0), MissileArray(500, 3))
    selectWeapon = 0
    Starships.score = 0
  }
}
