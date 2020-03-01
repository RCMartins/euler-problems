package euler.prob201_300.prob206

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob206 extends Util {

  def main(args: Array[String]): Unit = {

    val min = 1010101010
    val max = 1421374000

    var i = min

    while (i < max) {
      i += 10

      val big = BigInt(i)
      val str = (big * big).toString
      if (str.init.zipWithIndex.forall { case (c, index) => index % 2 == 1 || (c - '0' == index / 2 + 1) })
        result(i)
    }

  }

}
