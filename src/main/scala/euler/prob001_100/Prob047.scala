package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob047 extends UtilResult {
  def calc: Long = {
    def myfactors(n: Long): Set[Long] =
      factors(n).map(pair => math.pow(pair._1, pair._2).toLong).toSet

    val AMOUNT = 4

    def distinct4(n: Long, list: List[Set[Long]]): Long = {
      if (list.forall(set => set.size == AMOUNT)) {
        if (list.fold(Set())((s1, s2) => s1 ++ s2).size == AMOUNT * AMOUNT)
          return n
      }
      distinct4(n + 1, list.tail :+ myfactors(n + AMOUNT))
    }

    distinct4(1, (1 to AMOUNT).map(myfactors(_)).toList)
  }
}
