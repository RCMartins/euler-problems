package euler.prob101_200.prob188

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob188 extends Util {

  def main(args: Array[String]): Unit = {

    val modValue = BigInt(1e8.toInt)

    def hyper(a: BigInt, b: Int): BigInt = {
      if (b == 1) {
        a
      } else {
        a.modPow(hyper(a, b - 1), modValue)
      }
    }

    println(hyper(1777, 1855))

  }

}
