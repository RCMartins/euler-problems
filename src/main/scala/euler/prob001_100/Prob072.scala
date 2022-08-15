package euler.prob001_100

import euler.traits.UtilResult

import java.util.concurrent.atomic.AtomicInteger
import scala.collection.mutable
import scala.collection.parallel.CollectionConverters.ImmutableIterableIsParallelizable

/** Created by Ricardo
  */
object Prob072 extends UtilResult {

  def bruteForce(d: Int): Int = {
    val all = mutable.Set[RacValue]()

    for {
      a <- 1 until d
      b <- a + 1 to d
      rac = new RacValue(a, b)
      if rac.isReducedProperFraction
    } {
      all += rac
    }

    all.size
  }

  def calc: Long = {
    val target = 1000000

    val comp = ALL_COMPOSITE.takeWhile(_ <= target).map(_.toInt).toList
    val primes = SMALL_PRIMES.takeWhile(_ <= target).toList

    val progress = new AtomicInteger(0)

    val result: Long = {
      val compositeCount: Long =
        comp.par
          .map { n =>
            val allFactors: List[Int] = factors(n).map(_._1.toInt)
            if (n % 1000 == 0) {
              val v = progress.incrementAndGet()
              println(f"${v * 100.0 / 1000}%5.1f%%")
            }

            (1 to n).count(v => !allFactors.exists(v % _ == 0))
          }
          .foldLeft(0L)(_ + _)

      compositeCount + primes.map(_ - 1).foldLeft(0L)(_ + _)
    }

    result

    // answer: 303963552391
  }

}
