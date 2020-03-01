package euler.prob101_200.prob131

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob131 extends Util {

  def main(args: Array[String]): Unit = {

    val MAX = 1e6.toInt // 1e6.toInt

    val primes = SMALL_PRIMES.takeWhile(_ < MAX)

    val cubes = Stream.from(1).map(x => x.toLong * x * x).takeWhile(x => x > 0 && x < 1e18).toSet

    time {
      val v =
        for {
          p <- primes
          _ <- (1 to p * 2).find(n => cubes(n * n * n + n * n * p))
        } yield p

      v.force
    }

  }

}
