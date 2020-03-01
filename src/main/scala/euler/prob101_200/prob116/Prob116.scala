package euler.prob101_200.prob116

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob116 extends Util {

  def main(args: Array[String]): Unit = {

    val factorials = (0 to 100).map(BigInt(_).factorial).toVector

    //    n ! / r !(n - r) !
    def comb(n: Int, r: Int): BigInt = {
      println(s"C($n,$r)")
      factorials(n) / (factorials(r) * factorials(n - r))
    }

    //    def calc(nTiles: Int, size: Int): Long = {
    //      def calc(nTiles: Int, size: Int, amount: Int): Long = {
    //        comb(nTiles, amount).toLong
    //      }
    //
    //      (2 until MAX_SIZE).map(n => {
    //        if (MAX_SIZE + 1 - n)
    //          calc(, size, 1)
    //      }).sum
    //    }

    //    val v = for {
    //      tileSize <- 2 to 4
    //    } yield
    //      calc(MAX_SIZE, tileSize)

    val a = 50
    val v = for {
      size <- 2 to 4
      n <- 1 to a / size
    } yield comb(a - (size - 1) * n, n)

    println(v)
    result(v.sum)
    //20492570929
  }

}
