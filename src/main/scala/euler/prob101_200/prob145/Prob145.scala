package euler.prob101_200.prob145

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob145 extends Util {

  def main(args: Array[String]): Unit = {

    val max = 1e9.toInt
    var n = 1
    var count = 0

    while (n <= max) {
      if (n % 10 != 0) {
        val rev = n.toString.reverse.toInt
        if (digits(n + rev).forall(_ % 2 == 1))
          count += 1
      } else if (n % 1000000 == 0)
        println(n)

      n += 1
    }

    result(count)
  }

}
