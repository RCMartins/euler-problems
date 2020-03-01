package euler.prob101_200.prob178

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob178 extends Util {

  def main(args: Array[String]): Unit = {
    val printStuff = false

    time {
      val Size = 40

      val array = Array.ofDim[Long](Size, 10, 100)
      for {
        l <- 0 until Size
        d <- 0 to 9
        between <- 0 until 100
      } array(l)(d)(between) = -1

      def loopI(initialDeep: Int, digit: Int, xy: (Int, Int)): Long = {
        def loopStr(deep: Int, digit: Int, xy: (Int, Int)): Long = {
          if (printStuff)
            println("  " * (initialDeep - deep) + s"($deep, $digit, ${xy._1}/${xy._2})")
          val v = loop(deep, digit, xy)
          if (printStuff)
            println("  " * (initialDeep - deep) + s"$v <== ($deep, $digit, ${xy._1}/${xy._2})")
          v
        }

        def calcIndex(x: Int, y: Int): Int = {
          if (x > y) 10
          else x * 10 + y
        }

        def loop(deep: Int, digit: Int, xy: (Int, Int)): Long = xy match {
          case (x, y) =>
            if (deep == 0) {
              if (x > y) 1 else 0
            } else {
              val index = calcIndex(x, y)

              if (array(deep)(digit)(index) == -1) {
                array(deep)(digit)(index) = {
                  var sum = 0L
                  if (x > y) {
                    if (digit > 0)
                      sum += loopStr(deep - 1, digit - 1, xy)
                    if (digit < 9)
                      sum += loopStr(deep - 1, digit + 1, xy)
                  } else {
                    if (digit > 0) {
                      if (digit - 1 == y)
                        sum += loopStr(deep - 1, digit - 1, (x, y - 1))
                      else if (digit - 1 == x)
                        sum += loopStr(deep - 1, digit - 1, (x + 1, y))
                      else
                        sum += loopStr(deep - 1, digit - 1, (x, y))
                    }
                    if (digit < 9) {
                      if (digit + 1 == x)
                        sum += loopStr(deep - 1, digit + 1, (x + 1, y))
                      else if (digit + 1 == y)
                        sum += loopStr(deep - 1, digit + 1, (x, y - 1))
                      else
                        sum += loopStr(deep - 1, digit + 1, (x, y))
                    }
                  }
                  sum
                }
              }
              array(deep)(digit)(index)
            }
        }

         loopStr(initialDeep, digit, xy)
      }

      val v =
        for {
          size <- 1 to 39
          n <- (1 to 8).map(d => loopI(size, d, (0, 9))) :+ loopI(size, 9, (0, 8))
        } yield n

      v.sum
    }
  }

}
