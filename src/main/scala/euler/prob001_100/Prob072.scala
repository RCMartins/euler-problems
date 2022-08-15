package euler.prob001_100

import euler.traits.UtilResult

import scala.annotation.tailrec
import scala.collection.immutable.{SortedMap, SortedSet}
import scala.collection.{immutable, mutable}

/** Created by Ricardo
  */
object Prob072 extends UtilResult {

  def bruteForce(d: Int): (Int, Int) = {
    val all = mutable.Set[RacValue]()
    val allPrimes = mutable.Set[RacValue]()

    val isPrime = SMALL_PRIMES.takeWhile(_ <= d).toSet

    for {
      a <- 1 until d
      b <- a + 1 to d
      rac = new RacValue(a, b)
      if rac.isReducedProperFraction
    } {
      all += rac
      if (isPrime(b))
        allPrimes += rac
      if (b == 55)
        println(rac)
    }

    (all.size, allPrimes.size)
  }

  def calc: Long = {
    val target = 1000000

    // f(8) = 21
    // f(100) = 3043
    // f(1000) = 304191
    // f(2500) = 1899877

    val comp = ALL_COMPOSITE.takeWhile(_ <= target).map(_.toInt)
    val primes = SMALL_PRIMES.takeWhile(_ <= target)

    val smartNum: mutable.Map[List[Int], LazyList[Int]] = mutable.Map.empty

    smartNum += ((List(2), LazyList.from(1, 2)))

    def getStarmNum(list: List[Int]): LazyList[Int] =
      smartNum
        .getOrElseUpdate(
          list, {
            list match {
              case List(prime) =>
                LazyList.from(1).filter(_ % prime != 0)
              case headPrime :: others =>
                getStarmNum(others).filter(_ % headPrime != 0)
              case _ =>
                ???
            }
          }
        )

    val result: Long = {
      val compositeCount: Long =
        comp
          .map { n =>
            val allFactors: List[Int] = factors(n).map(_._1.toInt).reverse
            if (n % 10000 == 0)
              println(n)

//            var counter = 0

            @tailrec
            def countElems(list: LazyList[Int], counter: Int): Int = {
              list match {
                case elem #:: _ if elem > n => counter
                case _ #:: others           => countElems(others, counter + 1)
              }
            }
            countElems(getStarmNum(allFactors), 0)
//            getStarmNum(allFactors).takeWhile(_ <= n).size
//            (1 to n).count(v => !allFactors.exists(v % _ == 0))
          }
          .foldLeft(0L)(_ + _)

//      println(primes.size)
//      primes

      // (1 to target).map(n => primesMap.maxBefore(n - 1).map(_._2).getOrElse(0)).sum
      compositeCount + primes.map(_ - 1).foldLeft(0L)(_ + _)
    }

//    println()

//    println(("smart:", result))
//    println(("brute:", bruteForce(target)))

    result
  }

}
