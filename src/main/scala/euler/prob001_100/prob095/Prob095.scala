package euler.prob001_100.prob095

import euler.traits.UtilResult

import scala.collection.mutable

/**
  * Created by Ricardo
  */
object Prob095 extends UtilResult {
  def calc: Long = {
    val MAX = 1e6.toInt
    println(MAX)

    time {
      val next: Map[Int, Option[Int]] =
        (1 to MAX).map { n =>
          val sum = uniqueFactors(n).sum - n
          if (sum > MAX)
            n -> None
          else
            n -> Some(sum.toInt)
        }.toMap + (0 -> None)

      val chains =
        for {
          n <- 1 to MAX
          set = mutable.Set[Int]()
          if {
            def rec(numberOpt: Option[Int]): Boolean = {
              numberOpt match {
                case None =>
                  false
                case Some(number) =>
                  if (set(number)) {
                    false
                  } else if (number == n) {
                    true
                  } else {
                    set += number
                    rec(next(number))
                  }
              }
            }

            rec(next(n))
          }
        } yield set.size -> (set + n).min

      chains.maxBy(_._1)._2
    }
  }
}
