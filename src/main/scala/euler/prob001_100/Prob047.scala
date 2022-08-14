package euler.prob001_100

import euler.traits.UtilResult

import scala.annotation.tailrec

/** Created by Ricardo
  */
object Prob047 extends UtilResult {

  override def calc: Long = {
    def myFactors(n: Long): Set[Long] =
      factors(n).map(pair => math.pow(pair._1, pair._2).toLong).toSet

    val AMOUNT = 4

    @tailrec
    def distinct4(n: Long, list: List[Set[Long]]): Long = {
      if (
        list.forall(set => set.size == AMOUNT) &&
        list.fold(Set())((s1, s2) => s1 ++ s2).size == AMOUNT * AMOUNT
      )
        n
      else
        distinct4(n + 1, list.tail :+ myFactors(n + AMOUNT))
    }

    distinct4(1, (1 to AMOUNT).map(myFactors(_)).toList)
  }

}
