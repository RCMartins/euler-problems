package euler.prob101_200.prob160

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob160 extends Util {

  def main(args: Array[String]): Unit = {

    val mod = BigInt("1" + "0" * 20)
    println(mod)

    val v = (1 to 50).scanLeft(BigInt(1))((big, n) => {
      var b = (big * n) % mod
      while (b % 10 == 0)
        b = b / 10
      b
    })

    println(v.mkString("\n"))

  }

}
