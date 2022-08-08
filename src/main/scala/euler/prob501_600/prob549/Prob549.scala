package euler.prob501_600.prob549

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob549 extends Util {

  def main(args: Array[String]): Unit = {

//    val MAX = 1e8.toInt

//    val array: Array[Int] = Array.ofDim[Int](MAX)

    def fact(v: BigInt, n: Int): LazyList[BigInt] = {
      val newVal = v * n
      newVal #:: fact(newVal, n + 1)
    }

    val facts = fact(1, 1).zip(LazyList.from(1)) //.takeWhile(_._1 < MAX)

    println(facts.take(10).force)

    for {
      n <- 1 to 10000
    } yield {
      val v = facts.filter(_._1 % n == 0)
      if ( n % 100 == 0)
      println((n, v.head._2))
      v
    }

  }

}
