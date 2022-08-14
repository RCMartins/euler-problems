package euler.prob701_800

import euler.traits.UtilResult

/** Created by Ricardo
  */
object Prob710 extends UtilResult {

  override def calc: Long = {
    val target = 20
    val mod = 1000000
    val max = 1000
    val mem = Array.fill[Int](max, max)(-1)

    for (k <- 1 until max)
      mem(k)(k) = 1

    for (k <- 1 until max) {
      mem(0)(k) = 0
      mem(k)(0) = 0
    }

    mem(0)(0) = 1

    def f(n: Int, size: Int): Int =
      if (size <= 0)
        0
      else {
        val v = mem(n)(size)
        if (v != -1)
          v
        else {
          val newV: Int =
            (1 to n).map(v => mem(n - v)(size - 1)).foldLeft(0)((acc, v) => (acc + v) % mod)
          mem(n)(size) = newV
          newV
        }
      }

    def pf(n: Int, size: Int): Int =
      f(n, size).tap { v =>
        println(s"f($n, $size) = $v")
      }

    pf(1, 1)
    pf(2, 1)
    pf(2, 2)
    pf(3, 1)
    pf(3, 2)
    pf(3, 3)

    def t(n: Int): Int =
      ???

    t(target)
  }

}
