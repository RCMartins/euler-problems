package euler.prob301_400.prob347

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob347 extends Util {

  def main(args: Array[String]): Unit = {

    def M(p: Long, q: Long, n: Long): Long = {
      def Maux(acc: Long): Long = {
        if (acc > n)
          0L
        else
          math.max(acc, math.max(Maux(acc * p), Maux(acc * q)))
      }

      Maux(p * q)
    }

//    println(M(2, 3, 100))
//    println(M(3, 5, 100))
//    println(M(2, 73, 100))

    val MAX = 1e7.toInt

    val primes = SMALL_PRIMES.takeWhile(_ < MAX / 2)

    val pairs = primes.tails.takeWhile(_.nonEmpty).flatMap(pList => {
      val p1 = pList.head
      pList.drop(1).takeWhile(_ * p1 < MAX).map(p2 => (p1, p2))
    }).toStream

    time {
      pairs.map { case (p, q) => M(p, q, MAX) }.distinct.sum
    }
  }

}
