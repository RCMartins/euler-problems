package euler.prob101_200.prob134

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob134 extends Util {

  def main(args: Array[String]): Unit = {

    val MAX = 1e6.toInt
    val primes = SMALL_PRIMES.dropWhile(_ < 5)

    val pairs = primes.zip(primes.tail).takeWhile(_._1 <= MAX)

    time {
      val v = for {
        (p1, p2) <- pairs
      } yield {
        val value = {
          val str = p1.toString
          LazyList.from(1).map(n => (n.toString + str).toLong)
        }.filter(_ % p2 == 0).head
        value
      }

      v.sum
    }

    //18613426663617118
  }

}
