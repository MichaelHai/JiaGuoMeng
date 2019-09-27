sealed class Booster(val targetMatcher: TargetMatcher, val boosterRatio: Double) {
  def isMatch(building: Building): Boolean = {
    this.targetMatcher isMatch building
  }
}

case class SingleBuildingBooster(private val building: BuildingPrototype, override val boosterRatio: Double = 1) extends Booster(SingleBuildingMatcher(building), boosterRatio)
case class BuildingTypeBooster(private val buildingType: BuildingType, override val boosterRatio: Double) extends Booster(BuildingTypeMatcher(buildingType), boosterRatio)
case class AllBooster(override val boosterRatio: Double) extends Booster(AllMatcher, boosterRatio)