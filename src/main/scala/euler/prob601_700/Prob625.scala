package euler.prob601_700

import euler.traits.UtilResult

/** Created by Ricardo
  */
object Prob625 extends UtilResult {

  override def calc: Long = {
    def sum(n: Int): Long =
      (for (j <- 1 to n; i <- 1 to j) yield GCD(i, j)).sum

    (1 to 20).foreach { n =>
      println(s"G($n) = ${sum(n)}")
    }

    ???
  }

}
