package euler.prob101_200.prob160

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob160b extends Util {

  def main(args: Array[String]): Unit = {

    val mod = BigInt("1" + "0" * 25)
    println(mod)
    val MAX: Long = 1e6.toLong // 1e12.toLong
    println(MAX)

    @inline
    def removeZeros(big: BigInt): BigInt = {
      if (big % 10 == 0)
        removeZeros(big / 10)
      else
        big
    }

    @inline
    def removeZeros2(big: BigInt, count: Int = 0): (BigInt, Int) = {
      if (big % 10 == 0)
        removeZeros2(big / 10, count + 1)
      else
        (big, count)
    }

    var m = 0
    var totalZeros = 0L

    @inline
    def mult(n1: BigInt, n2: BigInt) = {
      val b = (n1 * removeZeros(n2)) % mod
      val (bigFinal, count) = removeZeros2(b)
      totalZeros += count
      if (count > m) {
        m = count
        println(s"NZeros: $m    ($n1, $n2)")
      }
      bigFinal
    }

    @inline
    def calc(bigInitial: BigInt, start: Long, end: Long): BigInt = {
      var n: Long = start
      var big = bigInitial
      while (n <= end) {
        big = mult(big, n)
        n += 1
      }
      big
    }

    //    v = (1 to MAX).par.aggregate(calc)

    time {
      val v = calc(BigInt(1), 1, MAX)
      println(totalZeros)
      v
    }

    //    while (n <= MAX) {
    //      big = (big * n) % mod
    //      while (big % 10 == 0) {
    //        big = big / 10
    //        if (n > 20 && big < 100000)
    //          println("problem")
    //
    //      }
    //      n += 1
    //    }

    //    println(big)

  }

}
