import scala.collection.mutable

class Playground extends BuildingInitializer{
  private val _buildings: mutable.Set[Building] = mutable.Set()
  private val _policies: mutable.Set[Policy] = mutable.Set()
  private val _photos: mutable.Set[Photo] = mutable.Set()

  def +(building: Building): Unit = {
    this._buildings add building
  }

  def +(policy: Policy): Unit = {
    this._policies add policy
  }

  def +(photo: Photo): Unit = {
    this._photos add photo
  }

  def calculateTotal: Double = {
    _buildings.toList.map(calculateBuildingOutput).sum
  }

  private def calculateBuildingOutput(building: Building): Double = {
    val value = _buildings.flatMap(_.boosters)
    val d = boostersRatio(value)(building)
    d *
      boostersRatio(_policies.flatMap(_.boosters))(building) *
      boostersRatio(_photos.flatMap(_.boosters))(building) *
      building.baseOutput
  }

  private def boostersRatio(boosters: IterableOnce[Booster])(building: Building) = {
    boosters.filter(booster => booster isMatch building).map(_.boosterRatio).sum + 1;
  }
}
