package euler.prob001_100

import euler.traits.UtilResult

import scala.collection.mutable

/**
  * Created by Ricardo
  */
object Prob014 extends UtilResult {
  override def calc: Long = {
    val memory = new mutable.HashMap[Long, Int]()

    def f(n: Long, counter: Int): Int = {
      if (memory.get(n).isDefined)
        memory(n) + counter
      else {
        if (n == 1)
          counter
        else if (n % 2 == 0)
          f(n / 2, counter + 1)
        else
          f(n * 3 + 1, counter + 1)
      }
    }

    memory += 1L -> 1
    val v = (2L until 1000000L)
      .map { elem =>
        val size = f(elem, 0)
        memory += elem -> size
        (size, elem)
      }
      .sortWith((a, b) => a._1 > b._1)
      .head
    v._2
  }
}
