sealed abstract class Policy(val boosters: List[Booster])

case object ZiYouMaoYiQuJianShe extends Policy(List(BuildingTypeBooster(Commercial, 3)))
