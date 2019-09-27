object Main {
  def main(args: Array[String]): Unit = {
    val allBuildings: Seq[Building] = Seq(3 * BianLiDian, 3 * CaiShiChang, 3 * JuMingLou, 3 * ZaoZhiChang, 2 * GangJieGouFang, 2 * FuZhuangDian, 2 * WuJinDian, 2 * MuWu, 2 * PingFang, 2 * MuCaiChang, 2 * ShiPinChang, 2 * FangZhiChang, XueXiao, TuShuCheng, MinShiZhai, GangTieChang, LinJianChang);
    val groupedBuildings: Map[BuildingType, Seq[Building]] = allBuildings.groupBy(_.buildingType);

    val residential = groupedBuildings.getOrElse(Residential, Seq.empty)
    val commercial = groupedBuildings.getOrElse(Commercial, Seq.empty)
    val industrial = groupedBuildings.getOrElse(Industrial, Seq.empty)

    var max: Double = 0;
    var maxPlayground: Playground = null
    residential.foreach(
      r1 => residential.filterNot(_ == r1).foreach(
        r2 => residential.filterNot(_ == r1).filterNot(_ == r2).foreach(
          r3 => commercial.foreach(
            c1 => commercial.filterNot(_ == c1).foreach(
              c2 => commercial.filterNot(_ == c1).filterNot(_ == c2).foreach(
                c3 => industrial.foreach(
                  i1 => industrial.filterNot(_ == i1).foreach(
                    i2 => industrial.filterNot(_ == i1).filterNot(_ == i2).foreach(
                      i3 => {
                        val playground = new Playground
                        playground + r1
                        playground + r2
                        playground + r3
                        playground + c1
                        playground + c2
                        playground + c3
                        playground + i1
                        playground + i2
                        playground + i3

                        playground + DongFangMingZhuDianShiTa
                        playground + ShiBoHuiZhongGuoGuan
                        playground + WaiTan
                        playground + PuDongXinQuZiMaoQu
                        playground + ShanghaiMeiShuDianYingZhiPianChang
                        playground + NanJingChangJiangDaQiao
                        playground + HuaXiCun
                        playground + HuaiYangCai
                        playground + YiXingZiShaHu
                        playground + XiHu
                        playground + YueJu
                        playground + ShiJieHuLianWangDaHui
                        playground + LuShuiQingShanJiuShiJinShanYinShanLiNian

                        playground + ZiYouMaoYiQuJianShe
                        playground + YiDaiYiLuJianShe
                        playground + QuYuXieTiaoFaZhan

                        playground + SingleBuildingBooster(BianLiDian, 1.5)
                        playground + SingleBuildingBooster(CaiShiChang, 1.5)

                        val total = playground.calculateTotal
                        if (total > max) {
                          maxPlayground = playground
                          max = total
                        }
                      }
                    )
                  )
                )
              )
            )
          )
        )
      )
    )

    println(maxPlayground);
  }
}
