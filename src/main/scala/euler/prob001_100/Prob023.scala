package euler.prob001_100

import euler.traits.UtilResult

import scala.annotation.tailrec

/** Created by Ricardo
  */
object Prob023 extends UtilResult {
  override def calc: Long = {
    val MAX = 28123

    def calcSums(a: Int, bs: List[Int]): List[Int] = {
      @tailrec
      def calcSumsAux(bs: List[Int], acc: List[Int]): List[Int] = {
        bs match {
          case Nil =>
            acc
          case b :: bs =>
            val sum = a + b
            if (sum > MAX) acc else calcSumsAux(bs, sum :: acc)
        }
      }
      calcSumsAux(bs, List())
    }

    val abundant = (1 until MAX).filter(number => uniqueFactors(number).init.sum > number).toList
    val sums = abundant.flatMap(a => calcSums(a, abundant)).toSet

    val set = (1 until MAX).toSet.diff(sums)
    set.sum
  }
}
