package euler.prob701_800

import euler.traits.UtilResult

import scala.annotation.tailrec

/** Created by Ricardo
  */
object Prob745 extends UtilResult {

  override def calc: Long = {
    val target = BigInt(10).pow(12)

    val perfect: LazyList[(Int, Long)] =
      LazyList.from(1).map(n => (n, n.toLong * n)).takeWhile(_._2 <= target).tail

    val fac: LazyList[(Long, List[(Long, Int)])] =
      perfect.map { case (n, sq) => sq -> factors(n).map { case (prime, k) => prime -> k * 2 } }

    println(fac.mkString("\n"))

    0
  }

}
