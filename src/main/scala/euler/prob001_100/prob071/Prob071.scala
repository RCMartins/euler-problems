package euler.prob001_100.prob071

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob071 extends UtilResult {
  def calc: Long = {
    val div3_7: Double = 3.0 / 7.0
    println(div3_7)

    def loop(a: Int, b: Int): (Int, Int) = {
      if (a + 3 <= 1000000 && b + 7 <= 1000000)
        loop(a + 3, b + 7)
      else
        (a, b)
    }

    loop(2, 5)._1
  }

  //    var best = 0.0
  //    var bestA = 0
  //    for {
  //      a <- 1 to 1000000
  //      b <- a + 1 to 1000000
  //      rac = RacValue(a, b)
  //      if rac.numerator == a && rac.denominator == b
  //      c = a.toDouble / b
  //      if c < div3_7
  //    } {
  //      if (c > best) {
  //        best = c
  //        bestA = a
  //        println(a + " / " + b + " = " + best)
  //      }
  //    }
  //    result(bestA)
}
