package euler.prob101_200.prob120

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob120 extends Util {

  def main(args: Array[String]): Unit = {

    val MAX_N = 1500

    val v = for {
      a <- 3 to 1000
      a2 = a * a
      r = (1 to MAX_N).map(n => (BigInt(a - 1).modPow(n, a2) + BigInt(a + 1).modPow(n, a2)).mod(a2)).max
    } yield {
      r
    }

    result(v.sum)

    //333082500
  }

}
