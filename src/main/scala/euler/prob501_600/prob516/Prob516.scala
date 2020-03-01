package euler.prob501_600.prob516

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob516 extends Util {

  def main(args: Array[String]): Unit = {

    val v = for {
      n <- 1 to 10
    } yield (1 to n).filter(GCD(_, n) == 1)

    println(v)

    val v2 = for {
      n <- 1 to 100
      if factors((1 to n).count(GCD(_, n) == 1)).forall { case (prime, _) => prime <= 5 }
    } yield n

    println(v2.sum)

  }

}
