package euler.prob501_600.prob600

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob600 extends Util {

  def main(args: Array[String]): Unit = {

    val Max: Int = 100
//    val Half: Int = Max / 2
    val Six: Int = Max / 6

    time {
      //      var count = 0
      val v =
        for {
          a <- 1 to Six
          b <- a to (Max - 2 * a) / 4
          c <- b to (Max - 2 * a - 2 * b) / 2
          //          b <- a to Half
          //          c <- b to Half
          //          if a + b + c <= Half
          if Set(a, b, c).size > 1
        }
        //        count += 1
          yield (a, b, c)

      println(v.mkString("\n"))
      v.size
      //      count
    }
  }

}
