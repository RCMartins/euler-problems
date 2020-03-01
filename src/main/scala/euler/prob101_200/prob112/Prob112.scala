package euler.prob101_200.prob112

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob112 extends Util {

  def main(args: Array[String]): Unit = {

    def inc(digits: Seq[Int]): Boolean = {
      var v = 0
      digits.forall { d =>
        if (d >= v) {
          v = d
          true
        } else
          false
      }
    }

    def dec(digits: Seq[Int]): Boolean = {
      var v = 10
      digits.forall { d =>
        if (d <= v) {
          v = d
          true
        } else
          false
      }
    }

    def isBouncy(n: Int): Boolean = {
      val dn = digits(n)
      !inc(dn) && !dec(dn)
    }

    var bunNum = 0
    var nBunNum = 0
    var n = 0
    while (bunNum != nBunNum * 99) {
      n += 1
      if (isBouncy(n))
        bunNum += 1
      else
        nBunNum += 1
    }

    result(n)

  }

}
