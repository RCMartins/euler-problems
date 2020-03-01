package euler.prob101_200.prob179

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob179 extends Util {

  def main(args: Array[String]): Unit = {

    val MAX = 1e7.toInt
    val ndivs = for {
      n <- 2 until MAX
    } yield {
      val size = uniqueFactors(n).size
      if (n % 100000 == 0) println(n)
      size
    }

    result(ndivs.zip(ndivs.tail).count(pair => pair._1 == pair._2))

    //986262 in 2-3 hours ?
  }

}
