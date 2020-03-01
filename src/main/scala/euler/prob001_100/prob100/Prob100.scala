package euler.prob001_100.prob100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob100 extends UtilResult {
  def calc: Long = {
    //    val SQRT_DIG = BigDecimal(150)
    //    val SQRT_PRE = BigDecimal(10).pow(SQRT_DIG.intValue())
    //
    //    /**
    //      * Private utility method used to compute the square root of a BigDecimal.
    //      *
    //      * @author Luciano Culacciatti
    //      *         url: http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
    //      */
    //    def sqrtNewtonRaphson(c: BigDecimal, xn: BigDecimal, precision: BigDecimal): BigDecimal = {
    //      val fx: BigDecimal = xn.pow(2) - c
    //      val fpx: BigDecimal = xn * 2
    //      var xn1: BigDecimal = fx.bigDecimal.divide(fpx.bigDecimal, 2 * SQRT_DIG.intValue, RoundingMode.HALF_DOWN)
    //      xn1 = xn - xn1
    //      val currentSquare: BigDecimal = xn1.pow(2)
    //      var currentPrecision: BigDecimal = currentSquare - c
    //      currentPrecision = currentPrecision.abs
    //      if (currentPrecision.compare(precision) <= -1) {
    //        xn1
    //      } else
    //        sqrtNewtonRaphson(c, xn1, precision)
    //    }
    //
    //    def bigSqrt(c: BigDecimal): BigDecimal = {
    //      sqrtNewtonRaphson(c, BigDecimal(1), BigDecimal(1) / SQRT_PRE)
    //    }

    //    def sqrt(value: BigDecimal): BigDecimal = {
    //      val x: BigDecimal = BigDecimal(Math.sqrt(value.doubleValue()))
    //      x + (value - (x * x).doubleValue() / (x.doubleValue() * 2.0))
    //    }

    def sqrtN(in: BigInt): BigInt = {
      val TWO = BigInt(2)
      var c: Int = 0

      // Significantly speed-up algorithm by proper select of initial approximation
      // As square root has 2 times less digits as original value
      // we can start with 2^(length of N1 / 2)
      var n0 = TWO.pow(in.bitLength / 2)
      // Value of approximate value on previous step
      var np = in

      do {
        // next approximation step: n0 = (n0 + in/n0) / 2
        n0 = (n0 + in / n0) / TWO

        // compare current approximation with previous step
        c = np.compare(n0)

        // save value as previous approximation
        np = n0

        // finish when previous step is equal to current
      } while (c != 0)

      n0
    }

    def quadratic(t: BigInt, show: Boolean): Option[BigInt] = {
      var c: BigInt = t * t - t
      if (c % 2 != 0)
        return None
      c = 1 + c * 2
      val sq = sqrtN(c)

      if (show)
        print((sq, sq * sq, c))

      if (sq * sq != c)
        return None

      if (true || show)
        print(sq + 1)

      val v1 = 1 + sq
      if (v1 % 2 == 0)
        Some(v1 / 2)
      else
        None
    }

    val tstart = BigInt(10).pow(12)

    var tadd = 2372000000L

    while (true) {
      tadd += 1
      val show = tadd % 1000000 == 0
      if (show) print((tadd, "  "))
      val x = quadratic(tstart + tadd, show)
      if (show) println()
      if (x.isDefined) {
        return x.get.toLong
      }
    }

    ???
  }
}
