import org.scalatest.Matchers._
import org.scalatest._

class PlaygroundSpec extends FlatSpec {
  "Empty playground" should "calculate as 0" in {
    val playground = new Playground

    playground.calculateTotal should equal(0)
  }

  "One single building" should "calculate as its output" in {
    val playground = new Playground
    playground + BianLiDian

    playground.calculateTotal should equal(1)
  }

  "Buildings" should "be calculate as its total output" in {
    val playground = new Playground
    playground + BianLiDian
    playground + CaiShiChang

    playground.calculateTotal should equal(2)
  }

  it should "add boosters together" in {
    val playground = new Playground
    playground + FangZhiChang
    playground + BianLiDian
    playground + CaiShiChang
    playground + JuMingLou

    playground.calculateTotal should equal(6.3)
  }

  it should "multiply baseOutput by stars" in {
    val playground = new Playground
    playground + 3 * FangZhiChang

    playground.calculateTotal should equal(6)
  }

  it should "multiply booster" in {
    val playground = new Playground
    playground + 3 * BianLiDian
    playground + 2 * JuMingLou

    playground.calculateTotal should equal(26)
  }

  "BianLiDian and JuMingLou" should "boost each other" in {
    val playground = new Playground
    playground + BianLiDian
    playground + JuMingLou

    playground.calculateTotal should equal(4)
  }

  "Fangzhichang" should "boost all commercial buildings" in {
    val playground = new Playground
    playground + FangZhiChang
    playground + BianLiDian
    playground + GangJieGouFang
    playground + CaiShiChang

    playground.calculateTotal should equal(4.3)
  }

  "Policy booster" should "work by multiply with other kinds of boosters" in {
    val playground = new Playground
    playground + FangZhiChang
    playground + BianLiDian
    playground + GangJieGouFang
    playground + JuMingLou
    playground + ZiYouMaoYiQuJianShe

    playground.calculateTotal should equal(12.6)
  }

  "Photo booster" should "work by multiply with other kinds of boosters" in {
    val playground = new Playground
    playground + FangZhiChang
    playground + BianLiDian
    playground + GangJieGouFang
    playground + JuMingLou
    playground + ZiYouMaoYiQuJianShe
    playground + DongFangMingZhuDianShiTa

    playground.calculateTotal should equal(13.86 +- 0.001)
  }
}