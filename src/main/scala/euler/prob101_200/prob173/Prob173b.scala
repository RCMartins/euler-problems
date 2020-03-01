package euler.prob101_200.prob173

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob173b extends Util {

  def main(args: Array[String]): Unit = {

    // Solution by Ana Filipa

    var r = 0

    def sol(n: Int, count: Int): Int = {
      val a = n / count + 4
      val b = a / 4
      val c = b - (count + 1)

      if (c > 0) {
        r += c
        println(count + 1)
        sol(n, count + 1)
      }

      ???
    }

    sol(1e6.toInt, 1)
    result(r)
  }

}
