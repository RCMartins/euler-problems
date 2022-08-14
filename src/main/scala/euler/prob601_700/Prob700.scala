package euler.prob601_700

import euler.traits.UtilResult

import scala.annotation.tailrec

/** Created by Ricardo
  */
object Prob700 extends UtilResult {
  override def calc: Long = {
    val base = 1504170715041707L
    val mod = 4503599627370517L

    @tailrec
    def loop(total: Long, last2: Long, last: Long): Long =
      if (last <= 1)
        total
      else {
        val numUpdated: Long = ((last2 / last + 1) * last) - last2
        loop(total + numUpdated, last, numUpdated)
      }

    loop(
      total = base,
      last2 = mod,
      last = base
    )
  }
}
