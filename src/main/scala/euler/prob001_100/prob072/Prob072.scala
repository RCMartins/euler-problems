package euler.prob001_100.prob072

import euler.traits.UtilResult

import scala.collection.mutable

/**
  * Created by Ricardo
  */
object Prob072 extends UtilResult {
  def calc: Long = {
      val MAX = 5000

      val all = mutable.Set[RacValue]()

      for {
        a <- 1 to MAX
        b <- a + 1 to MAX
        rac = new RacValue(a, b)
        if rac.isReducedProperFraction && !all(rac)
      } {
        all += rac
      }

      all.size
  }
}
