package euler.prob101_200.prob124

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob124 extends Util {

  def main(args: Array[String]): Unit = {

    val ndivs = for {
      n <- 1 to 100000
    } yield (n, factors(n).unzip._1.product)

    val sorted = ndivs.sortBy(_._2)
    result(sorted(10000 - 1)._1) // index starts at 0

  }

}
