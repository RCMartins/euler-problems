package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob046 extends UtilResult {
  def calc: Long = {
    val oddComp = ALL_COMPOSITE.filter(_ % 2 == 1)
    val squares: LazyList[Long] = LazyList.from(1).map { n =>
      2 * n.toLong * n.toLong
    }

    val v = for {
      comp <- oddComp
      if !squares.takeWhile(_ < comp).exists(sq => testIsPrime(comp - sq))
    } yield comp

    v.head
  }
}
