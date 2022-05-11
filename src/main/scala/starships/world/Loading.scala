package starships.world

import processing.core.PApplet
import starships.hostiles.{Ancalagon, Combator, Precursor}
import starships.obstacles.Wall
import starships.projectiles.{EnergyOrb, MachineGunProjectile, Missile}
import starships.weapons.{MachineGun, MissileArray}

object Loading {
  def loadstuff(p: PApplet): Unit = {

    Player.loadImages(p)
    Combator.loadImages(p)
    Precursor.loadImages(p)
    Ancalagon.loadImages(p)
    Missile.loadImages(p)
    EnergyOrb.loadImages(p)
    Wall.loadImages(p)
    Border.loadImages(p)
    Explosion.loadImages(p)
    MachineGunProjectile.loadImages(p)
    MachineGun.loadSounds(p)
    Ancalagon.loadSounds(p)
    Combator.loadSounds(p)
    MissileArray.loadSounds(p)
  }
}
