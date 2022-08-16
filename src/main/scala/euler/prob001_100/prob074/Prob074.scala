package euler.prob001_100.prob074

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob074 extends UtilResult {
  override def calc: Long = {
    val fact = (1 to 9).scanLeft(1)(_ * _)

    println(fact)

    def loop(init: List[Int], n: Int): Int = {
      val next = digits(n).map(fact).sum
      if (init.contains(next))
        1
      else {
        1 + loop(next :: init, next)
      }
    }

    val v = for {
      number <- 1 until 1000000
      size = loop(List(number), number)
      if size == 60
    } yield number

    //println(v)

    v.size
  }
}
