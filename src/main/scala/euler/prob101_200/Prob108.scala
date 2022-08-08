package euler.prob101_200

import euler.traits.UtilResult
import org.scalactic.Tolerance.convertNumericToPlusOrMinusWrapper
import org.scalactic.TypeCheckedTripleEquals.convertToCheckingEqualizer

import scala.collection.mutable
import scala.util.chaining.scalaUtilChainingOps

/** Created by Ricardo
  */
object Prob108 extends UtilResult {

  def calc: Long = {
    LazyList
      .from(1000)
      .find { n =>
        val resultSets: mutable.Set[(Int, Int)] = mutable.Set.empty
        resultSets += ((1, n + 1))

        (n + 2 to n * 2).foreach { x =>
          val rem = x - n

          val denominator = n.toLong * x
          if ((denominator / rem) * rem == denominator) {
            val d2 = (denominator / rem).toInt
            resultSets += ((x, d2))
          }
        }
        if (n % 10000 == 0)
          println(n)
        resultSets.size > 1000
      }
      .get

    // answer: 180180
  }
}
