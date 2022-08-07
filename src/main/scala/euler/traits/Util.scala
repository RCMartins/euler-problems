package euler.traits

import java.io.{BufferedReader, File, IOException, InputStreamReader}
import java.math.RoundingMode

import scala.annotation.tailrec
import scala.collection.{Iterable, mutable}
import scala.language.implicitConversions

/** Created by Ricardo
  */
trait Util {
  def NATURALS: LazyList[Int] = LazyList.from(1)

  private[Util] def br = new BufferedReader(new InputStreamReader(System.in))

  def readLine(reader: BufferedReader = br): Option[String] = {
    try {
      Some(reader.readLine())
    } catch {
      case _: IOException => None
    }
  }

  def readData(filePath: String): String = {
    val source = scala.io.Source.fromFile(new File("data", filePath))
    try source.mkString.trim
    finally source.close()
  }

  private val isPrimeMap = mutable.HashMap[Long, Boolean]()
  isPrimeMap += 1L -> false

  def testIsPrime(n: Long, primesSoFar: Iterable[Long] = ALL_PRIMES): Boolean = {
    isPrimeMap.get(n) match {
      case Some(result) => result
      case None =>
        primesSoFar.foreach { prime =>
          if (prime * prime > n) {
            isPrimeMap += n -> true
            return true
          } else if (n % prime == 0) {
            isPrimeMap += n -> false
            return false
          }
        }
        false
    }
  }

  private def primeLong(n: Long, primesSoFar: Vector[Long]): LazyList[Long] = {
    if (testIsPrime(n, primesSoFar)) {
      LazyList.cons(n, primeLong(n + 2, primesSoFar :+ n))
    } else
      primeLong(n + 2, primesSoFar)
  }

  val ALL_PRIMES: LazyList[Long] = 2L #:: primeLong(3L, Vector(2L))

  @tailrec
  final def testIsPrimeLongNoCache(n: Long, primesSoFar: List[Long]): Boolean = primesSoFar match {
    case Nil => false
    case prime :: others =>
      if (prime * prime > n) {
        true
      } else if (n % prime == 0) {
        false
      } else
        testIsPrimeLongNoCache(n, others)
  }

  def testIsPrimeIntNoCache(n: Int, primesSoFar: Iterable[Int]): Boolean = {
    primesSoFar.foreach { prime =>
      if (prime * prime > n) {
        return true
      } else if (n % prime == 0) {
        return false
      }
    }
    false
  }

  private def primeInt(n: Int, primesSoFar: Vector[Int]): LazyList[Int] = {
    if (testIsPrimeIntNoCache(n, primesSoFar)) {
      LazyList.cons(n, primeInt(n + 2, primesSoFar :+ n))
    } else
      primeInt(n + 2, primesSoFar)
  }

  val SMALL_PRIMES: LazyList[Int] = 2 #:: primeInt(3, Vector(2))

  private def compositeNumbers(n: Long, primes: LazyList[Long]): LazyList[Long] = {
    if (n == primes.head)
      compositeNumbers(n + 1, primes.tail)
    else
      n #:: compositeNumbers(n + 1, primes)
  }

  val ALL_COMPOSITE: LazyList[Long] = compositeNumbers(2, ALL_PRIMES)

  def factors(n: Long): List[(Long, Int)] = {
    def factorsAux(_n: Long, primesToCheck: LazyList[Long]): List[(Long, Int)] = {
      if (_n == 1)
        Nil
      else
        primesToCheck match {
          case prime #:: others =>
            var n = _n
            var count = 0
            while (n % prime == 0) {
              count += 1
              n /= prime
            }
            if (count == 0) factorsAux(n, others) else (prime, count) :: factorsAux(n, others)
        }
    }

    factorsAux(n, ALL_PRIMES)
  }

  def factors(big: BigInt): List[(Long, Int)] = {
    def factorsAux(_big: BigInt, primesToCheck: LazyList[Long]): List[(Long, Int)] = {
      if (_big == 1)
        Nil
      else
        primesToCheck match {
          case prime #:: others =>
            var n = _big
            var count = 0
            while (n % prime == 0) {
              count += 1
              n /= prime
            }
            if (count == 0) factorsAux(n, others) else (prime, count) :: factorsAux(n, others)
        }
    }

    factorsAux(big, ALL_PRIMES)
  }

  /** return all factors of n (1 and n included)
    */
  def uniqueFactors(n: Long): List[Long] = {
    val allFactors = factors(n).flatMap { case (prime, times) =>
      List.fill(times)(prime)
    }
    val allUniqueDivisors = allFactors
      .foldLeft(List(1L))((list, prime) => list.flatMap(elem => List(elem, elem * prime)))
      .distinct
      .sorted
    allUniqueDivisors
  }

  def uniqueFactors(n: BigInt): List[Long] = {
    val allFactors = factors(n).flatMap { case (prime, times) =>
      List.fill(times)(prime)
    }
    val allUniqueDivisors = allFactors
      .foldLeft(List(1L))((list, prime) => list.flatMap(elem => List(elem, elem * prime)))
      .distinct
      .sorted
    allUniqueDivisors
  }

  private def fib(a: Int, b: Int): LazyList[Int] = {
    LazyList.cons(a + b, fib(b, a + b))
  }

  val ALL_FIB: LazyList[Int] = 1 #:: 2 #:: fib(1, 2)

  def exponents(base: Long): LazyList[Long] = {
    def exponentsAux(number: Long): LazyList[Long] = {
      number #:: exponentsAux(number * base)
    }

    exponentsAux(1)
  }

  def exponents(base: Long, exp: Int): Long = exponents(base).drop(exp).head

  def result(result: Any): Unit = {
    val resultStr = "#####  " + result.toString + "  #####"
    println()
    println("#" * resultStr.length)
    println(resultStr)
    println("#" * resultStr.length)
  }

  def remove1Digit(number: Int, digit: Char): Int = {
    val index = number.toString.indexOf(digit)
    (number.toString.take(index) + number.toString.drop(index + 1)).toInt
  }

  def removeAllDigits(number: Int, digit: Char): Int = number.toString.filter(_ != digit).toInt

  def digits(number: Int): Seq[Int] = number.toString.map(_.asDigit)

  def digits(number: Long): Seq[Int] = number.toString.map(_.asDigit)

  def digits(number: String): Seq[Int] = number.map(_.asDigit)

  def GCD(a: Long, b: Long): Long = if (b == 0) a else GCD(b, a % b)

  def LCM(a: Long, b: Long): Long = (a / GCD(a, b)) * b

  class RacValue(numerator_in: Long, denominator_in: Long) {
    val (numerator: Long, denominator: Long) = {
      val gcdValue = if (denominator_in == 0) 1 else GCD(numerator_in, denominator_in)
      (numerator_in / gcdValue, denominator_in / gcdValue)
    }

    def *(other: RacValue): RacValue =
      new RacValue(this.numerator * other.numerator, this.denominator * other.denominator)

    def get: (Long, Long) = (numerator, denominator)

    def isReducedProperFraction: Boolean =
      numerator_in == numerator && denominator_in == denominator

    def canEqual(other: Any): Boolean = other.isInstanceOf[RacValue]

    override def equals(other: Any): Boolean = other match {
      case that: RacValue =>
        (that canEqual this) &&
        numerator == that.numerator &&
        denominator == that.denominator
      case _ => false
    }

    override lazy val hashCode: Int = {
      val state = Seq(numerator, denominator)
      state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
    }
  }

  class BigIntImproved(n: BigInt) {
    def isPalindrome: Boolean = {
      val str = n.toString
      str == str.reverse
    }

    def reverseDigits: BigInt = BigInt(n.toString.reverse)

    def sumDigits: Int = n.toString.view.map(_.asDigit).sum

    // http://www.codecodex.com/wiki/Calculate_an_integer_square_root
    def sqrt(): BigInt = {
      val number = n

      def next(n: BigInt, i: BigInt): BigInt = (n + i / n) >> 1

      val one = BigInt(1)

      var n2 = one
      var n1 = next(n2, number)

      while ((n1 - n2).abs > one) {
        n2 = n1
        n1 = next(n2, number)
      }

      while (n1 * n1 > number) {
        n1 -= one
      }

      n1
    }

    def factorial: BigInt = {
      @tailrec
      def factorialAux(n: BigInt, acc: BigInt): BigInt = {
        if (n <= 1)
          if (acc < 1) 1 else acc
        else {
          val n1 = n - 1
          factorialAux(n1, acc * n1)
        }
      }

      factorialAux(n, n)
    }

    def factorialMod(mod: BigInt): BigInt = {
      @tailrec
      def factorialAux(n: BigInt, acc: BigInt): BigInt = {
        if (n <= 1)
          if (acc < 1) 1 else acc
        else {
          val n1 = n - 1
          factorialAux(n1, (acc * n1) % mod)
        }
      }

      factorialAux(n, n)
    }
  }

  implicit def bigIntImproved(n: BigInt): BigIntImproved = new BigIntImproved(n)

  private val SQRT_DIG: BigDecimal = BigDecimal(1000)
  private val SQRT_PRE: BigDecimal = BigDecimal(10).pow(SQRT_DIG.intValue)

  /** Private utility method used to compute the square root of a BigDecimal.
    *
    * url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
    *
    * @author
    *   Luciano Culacciatti
    */
  private def sqrtNewtonRaphson(
      c: BigDecimal,
      xn: BigDecimal,
      precision: BigDecimal
  ): BigDecimal = {
    val fx: BigDecimal = xn.pow(2) - c
    val fpx: BigDecimal = xn * BigDecimal(2)
    var xn1: BigDecimal =
      fx.bigDecimal.divide(fpx.bigDecimal, 2 * SQRT_DIG.intValue, RoundingMode.HALF_DOWN)
    xn1 = xn - xn1
    val currentSquare: BigDecimal = xn1.pow(2)
    var currentPrecision: BigDecimal = currentSquare - c
    currentPrecision = currentPrecision.abs
    if (currentPrecision.compareTo(precision) <= -1) {
      xn1
    } else
      sqrtNewtonRaphson(c, xn1, precision)
  }

  /** Uses Newton Raphson to compute the square root of a BigDecimal.
    *
    * url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
    *
    * @author
    *   Luciano Culacciatti
    */
  def bigSqrt(c: BigDecimal): BigDecimal = {
    sqrtNewtonRaphson(c, BigDecimal(1), BigDecimal(1) / SQRT_PRE)
  }

  def time(): Long = System.currentTimeMillis()

  def time[A](f: => A, useResult: Boolean = true): A = {
    val t = System.currentTimeMillis()
    val res = f
    val totalTime = System.currentTimeMillis() - t
    if (useResult)
      result(res)
    else
      println("Result: " + res)
    println(s"Total time: ${totalTime}ms")
    res
  }

  def timeOnly[A](f: => A): A = {
    val t = System.currentTimeMillis()
    val res = f
    val totalTime = System.currentTimeMillis() - t
    println(s"Total time: ${totalTime}ms")
    res
  }

  def timeOnlyNanos[A](f: => A): A = {
    val t = System.nanoTime()
    val res = f
    val totalTime = System.nanoTime() - t
    println(s"Total time: ${totalTime}nanos")
    res
  }

  def isPerfectSquare(n: Long): Boolean = {
    if (n < 0)
      false
    else {
      val tst: Long = (Math.sqrt(n) + 0.5).toLong
      tst * tst == n
    }
  }

  def isPerfectSquare(n: BigInt): Boolean = {
    if (n < 0)
      false
    else {
      val tst = n.sqrt()
      tst * tst == n
    }
  }
}
