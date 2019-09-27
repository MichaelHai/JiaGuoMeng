sealed abstract class Photo(val boosters: List[Booster])

case object DongFangMingZhuDianShiTa extends Photo(List(AllBooster(0.1)))
