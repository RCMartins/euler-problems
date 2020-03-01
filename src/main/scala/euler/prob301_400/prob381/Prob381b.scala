package euler.prob301_400.prob381

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob381b extends Util {

  def main(args: Array[String]): Unit = {

    //List(4, 4, 1, 11, 6, 2, 14, 25, 19, 32, 15, 5, 29, 46, 7, 53, 8, 44, 27, 49, 10, 33, 36)

    //    val MEMORY_SIZE = 20001
    //    val memo = Array.ofDim[BigInt](MEMORY_SIZE)
    //
    //    time {
    //      memo(0) = 1
    //      for (i <- 1 until MEMORY_SIZE) {
    //        memo(i) = memo(i - 1) * i
    //      }
    //      "done calc!"
    //    }
    //
    //    val MAX = 1e2 // 1e8.toInt
    //
    //    def s(p: Int): Int = {
    //      var total = BigInt(0)
    //      for (k <- 1 to 5) {
    //        total += BigInt(p - k).factorialMod(p)
    //      }
    //      (total % p).toInt
    //    }
    //
    //    def s2(p: Int): Long = {
    //      var smaller = BigInt(p - 5).factorialMod(p)
    //      var total = smaller
    //      for (k <- 4 to 1 by -1) {
    //        smaller = (smaller * (p - k)) % p
    //        total += smaller
    //      }
    //      (total % p).toLong
    //    }
    //
    //    time {
    //      val list = SMALL_PRIMES.dropWhile(_ < 5).takeWhile(_ < MAX).map(s2)
    //      //      println(list.toList)
    //      list.sum
    //    }

    {
      val MAX = 480 //1e7

      val primes = SMALL_PRIMES.dropWhile(_ < 5).takeWhile(_ < MAX)

//      for {
//        prime <- primes
//      }

      time(primes.size)
    }


    //    println(BigInt(3).factorialMod(7))

    //    val n = 1e6.toInt
    //    time(BigInt(n).factorialMod(n))
    //    time({BigInt(n).factorial; ()})

  }
}
