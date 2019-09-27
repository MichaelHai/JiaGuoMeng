import scala.collection.mutable

trait BuildingInitializer {
  BuildingInitializer
}

abstract class ResidentialBuilding extends BuildingPrototype(Residential)

abstract class CommercialBuilding extends BuildingPrototype(Commercial)

abstract class IndustrialBuilding extends BuildingPrototype(Industrial)

sealed class BuildingPrototype(val buildingType: BuildingType, val baseOutput: Double = 1) {
  private val _boosters: mutable.Set[Booster] = mutable.Set()

  def boosters: Set[Booster] = _boosters.toSet

  def >>(boostee: BuildingPrototype): BuildingPrototype = {
    this._boosters add SingleBuildingBooster(boostee)
    this
  }

  def >>(boostee: BuildingPrototype, ratio: Double): BuildingPrototype = {
    this._boosters add SingleBuildingBooster(boostee, ratio)
    this
  }

  def >>>(boosteeType: BuildingType, boosterRatio: Double): BuildingPrototype = {
    this._boosters add BuildingTypeBooster(boosteeType, boosterRatio)
    this
  }

  def >>>(boosterRatio: Double): BuildingPrototype = {
    this._boosters add AllBooster(boosterRatio)
    this
  }
}

sealed class Building(val prototype: BuildingPrototype, val star: Int) {
  def buildingType: BuildingType = prototype.buildingType
  def boosters: Set[Booster] = prototype.boosters
    .map(booster => new Booster(booster.targetMatcher, booster.boosterRatio * star))
  def baseOutput: Double = prototype.baseOutput * Building.starMultiplier(star)
}

object Building {
  val starMultiplier: Int => Int = {
    case 1 => 1
    case 2 => 2
    case 3 => 6
  }
}

case object BianLiDian extends CommercialBuilding

case object CaiShiChang extends CommercialBuilding

case object JuMingLou extends ResidentialBuilding

case object ZaoZhiChang extends IndustrialBuilding

case object GangJieGouFang extends ResidentialBuilding

case object FuZhuangDian extends CommercialBuilding

case object WuJinDian extends CommercialBuilding

case object MuWu extends ResidentialBuilding

case object PingFang extends ResidentialBuilding {
  override val baseOutput: Double = 1.1
}

case object MuCaiChang extends IndustrialBuilding

case object ShiPinChang extends IndustrialBuilding

case object FangZhiChang extends IndustrialBuilding

case object XueXiao extends CommercialBuilding

case object TuShuCheng extends CommercialBuilding

case object MinShiZhai extends CommercialBuilding {
  override val baseOutput: Double = 1.52
}

case object GangTieChang extends IndustrialBuilding

case object LinJianChang extends IndustrialBuilding

case object KongZhongBieShu extends ResidentialBuilding

case object FuXingGongGuan extends ResidentialBuilding

case object MeiTiZhiSheng extends CommercialBuilding

case object QiEJiXie extends IndustrialBuilding

case object RenMinShiYou extends IndustrialBuilding

case object RenCaiGongYu extends ResidentialBuilding

case object HuaYuanYangFang extends ResidentialBuilding

case object ZhongShiXiaoLou extends ResidentialBuilding

case object ShangMaoZhongXin extends CommercialBuilding

case object JiaYouZhan extends CommercialBuilding

case object XiaoXingGongYu extends ResidentialBuilding

case object ShuiChang extends IndustrialBuilding

case object DianChang extends IndustrialBuilding

object BuildingPrototype {
  implicit def wrapStars(stars: Int): StarsWrapper = {
    new StarsWrapper(stars)
  }

  implicit def convertPrototypeToBuilding(prototype: BuildingPrototype): Building = {
    1 * prototype
  }

  class StarsWrapper(private val stars: Int) {
    def *(building: BuildingPrototype): Building = {
      new Building(building, stars)
    }
  }
}

object BuildingInitializer {
  BianLiDian >> JuMingLou
  CaiShiChang >> ShiPinChang
  JuMingLou >> BianLiDian
  ZaoZhiChang >> TuShuCheng
  GangJieGouFang >> GangTieChang
  FuZhuangDian >> FuZhuangDian
  WuJinDian >> LinJianChang
  MuWu >> MuCaiChang
  PingFang >>> (Residential, 0.2)
  MuCaiChang >> MuWu
  ShiPinChang >> CaiShiChang
  FangZhiChang >> FuZhuangDian >>> (Commercial, 0.15)
  XueXiao >> TuShuCheng
  TuShuCheng >> XueXiao >> ZaoZhiChang
  MinShiZhai >> KongZhongBieShu >>> 0.2
  GangTieChang >> GangJieGouFang >>> (Industrial, 0.15)
  LinJianChang >> WuJinDian >> QiEJiXie
  KongZhongBieShu >> MinShiZhai >>> 0.2
  MeiTiZhiSheng >>> 0.05
  QiEJiXie >> LinJianChang >>> 0.1
  RenMinShiYou >> JiaYouZhan
  RenCaiGongYu >>> (Industrial, 0.15) >>> 0.2
  HuaYuanYangFang >> ShangMaoZhongXin
  ZhongShiXiaoLou >>> (Residential, 0.15) >>> 0.2
  ShangMaoZhongXin >> HuaYuanYangFang
  JiaYouZhan >> (RenMinShiYou, 0.5)
  DianChang >>> 0.2
}

