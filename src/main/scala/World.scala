object World {
  var player = Player(64, 256, 0, 0.9f, 3, MachineGun(50, 1, 0))
  var projectilesList = List.empty[Projectile]
  var walls = List(Wall(1024, 0, 60, 100), Wall(1024, 412, 60, 100))
  var worldBorder = Border()
  var enemies = List(
    Enemy(900, 200, 0, 0.9f, 3),
    Enemy(900, 250, 0, 0.9f, 3),
    Enemy(925, 300, 0, 0.9f, 3)
  )
  def everything: List[Actor] = {
    player :: projectilesList ::: enemies
  }
}
