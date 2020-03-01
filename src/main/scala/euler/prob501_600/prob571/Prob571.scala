package euler.prob501_600.prob571

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob571 extends Util {

  def main(args: Array[String]): Unit = {

    //    implicit class StringToNumber(s: String) {
    //      def toInt(radix: Int): Int = Integer.parseInt(s, radix)
    //
    //      def toLong(radix: Int): Long = java.lang.Long.parseLong(s, radix)
    //    }

    //    val MAX_RADIX = 12
    //
    //    val pandigitals = "0123456789ab".permutations.toStream.map(n => java.lang.Long.parseLong(n.mkString, MAX_RADIX))
    //
    //    def superPan(n10: BigInt, radix: Int): Boolean = {
    //      if (radix == 1) true
    //      else n10.toString(radix).distinct.length == radix && superPan(n10, radix - 1)
    //    }
    //
    //    val v = for {
    //      pan <- pandigitals
    //      if superPan(pan, MAX_RADIX - 1)
    //    } yield
    //      BigInt(pan).toString(MAX_RADIX)
    //
    //    println(v.take(1))

    val MAX_RADIX = 12

    def pandigitals = "0123456789ab".permutations.filter(_.head != '0').map(n => BigInt(n.mkString, MAX_RADIX))

    println("...")

    //    var MAX = MAX_RADIX

    @inline
    def superPan(n10: BigInt, radix: Int): Boolean = {
      //      if (radix < MAX) {
      //        println("min", radix)
      //        MAX = radix
      //      }

      if (radix == 1) true
      else n10.toString(radix).distinct.length == radix && superPan(n10, radix - 1)
    }

    var tries = 0

    val v = for {
      pan <- pandigitals
      _ = {
        tries += 1; if (tries % 10000000 == 0) println(tries)
      }
      if superPan(pan, MAX_RADIX - 1)
    } yield
      pan //.toString(MAX_RADIX)

//    val all = for {
//      n <- Stream.from(1)
//    } yield (n, v.next()) //, v.take(n).sum)

    val all = v.toStream

    for {
      n <- 1 to 10
    } println((n, all.take(n).sum))

    result(all.take(10).sum)

  }

}
