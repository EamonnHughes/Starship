case class MachineGun(var fireRate: Int, var damage: Int, var overHeat: Int)
    extends Weapon {

  var time: Long = System.currentTimeMillis
  def shoot(): Unit = {
    val currentTime = System.currentTimeMillis

    if (currentTime > time + fireRate && overHeat < 100) {
      World.projectilesList = Projectile(
        World.player.x + 25,
        World.player.y + 10,
        1
      ) :: World.projectilesList
      overHeat += 1
      time = currentTime
    }
  }
}
