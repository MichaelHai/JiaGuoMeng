sealed abstract class Policy(val boosters: List[Booster])

case object ZiYouMaoYiQuJianShe extends Policy(List(BuildingTypeBooster(Commercial, 3)))
case object YiDaiYiLuJianShe extends Policy(List(AllBooster(0.75)))
case object QuYuXieTiaoFaZhan extends Policy(List(BuildingTypeBooster(Residential, 1.5)))
