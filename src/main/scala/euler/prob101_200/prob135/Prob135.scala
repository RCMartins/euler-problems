package euler.prob101_200.prob135

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob135 extends Util {

  def main(args: Array[String]): Unit = {

    val MAX = 1000000L

    val coll = for {
      dist <- 1 to 100000
      z <- 1 to 800000
      y = z + dist
      x = y + dist
      value = x.toLong * x.toLong - y.toLong * y.toLong - z.toLong * z.toLong
      if value > 0L && value < MAX
    } yield value

    println(coll.groupBy(x => x).count(_._2.size == 10))

    //3525  (?, ?)
    //3975  (?, ?)
    //4059  ------
    //3993  ------ (100000, 200000)
    //4313  (100000, 400000)
  }

}
