sealed trait TargetMatcher {
  def isMatch(building: Building): Boolean
}

case class SingleBuildingMatcher(private val building: BuildingPrototype) extends TargetMatcher {
  override def isMatch(building: Building): Boolean = {
    building.prototype == this.building
  }
}

case class BuildingTypeMatcher(private val buildingType: BuildingType) extends TargetMatcher {
  override def isMatch(building: Building): Boolean = {
    this.buildingType == building.buildingType
  }
}

case object AllMatcher extends TargetMatcher {
  override def isMatch(building: Building): Boolean = true
}