package euler.prob601_700

import euler.traits.UtilResult

import scala.annotation.tailrec

/**
  * Created by Ricardo
  */
object Prob700 extends UtilResult {
  def calc: Long = {
    val base = 1504170715041707L
    val base2 = base * 2
    val base3 = base * 3
    val mod = 4503599627370517L

    println(s"[count = 001]   total = $base   last = $base")

    @tailrec
    def loop(total: Long, last: Long, num: Long, count: Int): Long = {
      if (last <= 1)
        total
      else {
        val numUpdated: Long =
          if (num + base2 >= mod)
            num + base2 - mod
          else
            num + base3 - mod

        if (numUpdated < last) {
          println(f"[count = ${count + 1}%03d]   total = ${total + numUpdated}%d   last = $numUpdated%16d")
          loop(total + numUpdated, numUpdated, numUpdated, count + 1)
        } else {
          loop(total, last, numUpdated, count)
        }
      }
    }

    loop(base, base, base, 1)
  }
}
