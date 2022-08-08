package euler.prob701_800

import better.files.File
import euler.traits.UtilResult

import scala.annotation.tailrec
import scala.util.chaining.scalaUtilChainingOps

/** Created by Ricardo
  */
object Prob800 extends UtilResult {

  def calc: Long = {

    // C(800) = 2
    // C(800^800) = 10790
    // C(8000^8000) = 439558
    // C(80000^80000) = 22995743 ?       (Total time: 2143467ms)

    // Cmax(800, 2) = 7687

    val limitBase: Int = 800800
    val limit: BigInt =
      BigInt(limitBase).pow(limitBase)

    val bitLength = limit.bitLength
    println(s"bitLength = $bitLength")

    val possiblePrimes: List[Int] =
      SMALL_PRIMES.takeWhile(_ <= bitLength).toList
    val primesToIndex: Map[Int, Int] =
      possiblePrimes.zipWithIndex.toMap
    val indexToPrimes: Array[Int] =
      possiblePrimes.toArray
    val initialMaxIndex: Int =
      indexToPrimes.length - 1

    @inline
    def formula(p: Int, q: Int): BigInt =
      BigInt(p).pow(q) * BigInt(q).pow(p)

    @inline
    def valid(p: Int, q: Int): Boolean =
      p + q < limitBase ||
        timeOnly(formula(p, q)) <= limit // && { println((p, q)); true }

//    {
//      val n = 2
//
//      val afterLast =
//        primes.dropWhile(_ <= n).dropWhile(valid(n, _)).headOption
//
//      println(afterLast)
//    }

//    ???

    @tailrec
    def findLoop(
        headPrime: Int,
        min: Int,
        max: Int
    ): Int =
      if (max == min + 1)
        max
      else {
        val middle = (max + min) / 2
        if (valid(headPrime, indexToPrimes(middle)))
          findLoop(headPrime, middle, max)
        else
          findLoop(headPrime, min, middle)
      }

    val cacheFile: File = File(s"cache/prob800cache$limitBase.txt")
    val cache: Map[Int, Int] =
      if (limitBase == 800800)
        cacheFile.lines.map { case s"$key=$value" => (key.toInt, value.toInt) }.toMap
      else
        Map.empty

    def saveToCache(key: Int, value: Int): Unit =
      if (limitBase == 800800)
        cacheFile.appendLine(s"$key=$value")

    @tailrec
    def mainCountLoop(primes: List[Int], accCount: Int, maxIndex: Int): Int =
      primes match {
        case first :: second :: tail =>
          println(s"p=$first    (currentIndex=${primesToIndex(first)}, maxIndex=$maxIndex)")
          val cacheResult: Option[Int] = cache.get(first)
          if (cacheResult.nonEmpty || valid(first, second)) {
            val secondIndex = primesToIndex(second)
            val result: Int =
              cacheResult.getOrElse(
                findLoop(first, primesToIndex(second), maxIndex).tap(saveToCache(first, _))
              )
            mainCountLoop(
              second :: tail,
              accCount + result - secondIndex,
              result
            )
          } else
            accCount
        case _ =>
          accCount
      }

    mainCountLoop(possiblePrimes, accCount = 0, maxIndex = initialMaxIndex)
  }
}

//package euler.prob701_800
//
//import euler.traits.UtilResult
//
//import scala.annotation.tailrec
//
///** Created by Ricardo
//  */
//object Prob800 extends UtilResult {
//
//  def calc: Long = {
//
//    // C(800) = 2
//    // C(800^800) = 10790
//    // C(8000^8000) = 439558
//
//    val limitBase: Int = 800800
//    val limit: BigInt =
//      BigInt(limitBase).pow(limitBase)
//
//    val bitLength = limit.bitLength
//
//    val primesIndex: Map[Int, Int] =
//      SMALL_PRIMES.takeWhile(_ <= bitLength).zipWithIndex.toMap
//
//    println(s"limit.bitLength = ${limit.bitLength}")
//
//    def formula(p: Int, q: Int): BigInt =
//      BigInt(p).pow(q) * BigInt(q).pow(p)
//
//    def valid(p: Int, q: Int, softLimitBase: Int): Boolean =
//      p + q < limitBase || {
//        softLimitBase != limitBase && q < softLimitBase
//      } || {
//        formula(p, q) <= limit && { println((p, q)); true }
//      }
//
//    @tailrec
//    def countLoop(
//        headPrime: Int,
//        softLimitBase: Int,
//        otherPrimes: LazyList[Int],
//        count: Int
//    ): Int =
//      otherPrimes match {
//        case other #:: tail if valid(headPrime, other, softLimitBase) =>
//          countLoop(headPrime, softLimitBase, tail, count + 1)
//        case _ =>
//          count
//      }
//
//    println(limitBase)
//    val root2 = Math.sqrt(limitBase)
//    val root4 = Math.sqrt(Math.sqrt(limitBase))
//    val root8 = Math.sqrt(Math.sqrt(Math.sqrt(limitBase)))
//    val root16 = Math.sqrt(Math.sqrt(Math.sqrt(Math.sqrt(limitBase))))
//
//    @tailrec
//    def mainCountLoop(primes: LazyList[Int], accCount: Int): Int =
//      primes match {
//        case first #:: second #:: tail =>
//          val softOtherLimit =
//            if (first == 2) {
//              if (first < root16)
//                limitBase * 16
//              else if (first < root8)
//                limitBase * 8
//              else if (first < root4)
//                limitBase * 4
//              else if (first < root2)
//                limitBase * 2
//              else
//                limitBase
//            } else
//              limitBase
//
//          if (valid(first, second, softOtherLimit))
//            mainCountLoop(
//              second #:: tail,
//              accCount + countLoop(first, softOtherLimit, tail, count = 1)
//            )
//          else
//            accCount
//        case _ =>
//          accCount
//      }
//
//    mainCountLoop(SMALL_PRIMES, accCount = 0)
//  }
//}
