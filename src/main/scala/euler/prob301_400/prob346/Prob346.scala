package euler.prob301_400.prob346

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob346 extends Util {

  def main(args: Array[String]): Unit = {

    def repunit(base: Int): LazyList[Long] = {
      def loop(before: Long, baseN: Long): LazyList[Long] = {
        val n = baseN * base + before
         n #:: loop(n, baseN * base)
      }
      loop(base + 1, base)
    }

    val limit = Math.pow(10, 12).toLong
    val limitBase = Math.sqrt(limit).toInt

    val v = (2 to limitBase).flatMap(base => repunit(base).takeWhile(_ < limit))
    println(1 + v.distinct.sum) //1.2s - 336108797689259276
  }

}
