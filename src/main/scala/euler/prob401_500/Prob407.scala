package euler.prob401_500

import euler.traits.UtilResult

/** Created by Ricardo
  */
object Prob407 extends UtilResult {

  override def calc: Long = {

    def bruteForce(n: Int): Long = {
      (n - 1 to 1 by -1)
        .map(_.toLong)
        .find { a =>
          (a * a % n) == (a % n)
        }
        .getOrElse(0)
    }

    (10000000 to 1 by -1)
      .map { n =>
        if (n % 100 == 0)
          println(n)
        bruteForce(n)
      }
      .foldLeft(0L)(_ + _)
  }

}
