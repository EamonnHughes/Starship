object World {
  var player = Player(50, 50, 0, 0.95f)
  var projectilesList = List.empty[Projectile]
  var walls = List(Wall(1024, 0, 60, 60))
}
