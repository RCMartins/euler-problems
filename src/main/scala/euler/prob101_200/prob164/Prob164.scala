package euler.prob101_200.prob164

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob164 extends Util {

  final val DEBUG: Boolean = true

  def main(args: Array[String]): Unit = {

    val MAX_INDEX = 20

    def calc1(a: Int, b: Int, index: Int): Long = {
      if (index > MAX_INDEX) {
        1
      } else {
        val sum = a + b
        var total = 0L
        for (d <- 0 to 9 - sum) {
          if (DEBUG) {
            println(" " * (index - 1) + d)
          }
          total += calc1(b, d, index + 1)
        }
        total
      }
    }

    def calc2(a: Int, b: Int, index: Int): Long = {
      if (index == MAX_INDEX) {
        10 - a - b
      } else {
        val sum = a + b
        var total = 0L
        for (d <- 0 to 9 - sum) {
          if (DEBUG) {
            println(" " * (index - 1) + d)
          }
          total += calc2(b, d, index + 1)
        }
        total
      }
    }

    val memo = Array.ofDim[Long](10, 10)
    val SP_MAX = 8

    def calc3(a: Int, b: Int, index: Int): Long = {
      if (index == MAX_INDEX - SP_MAX) {
        memo(a)(b)
      } else {
        val sum = a + b
        var total = 0L
        for (d <- 0 to 9 - sum) {
          if (DEBUG) {
            println(" " * (index - 1) + d)
          }
          total += calc3(b, d, index + 1)
        }
        total
      }
    }

    def calcFirst(f: (Int, Int, Int) => Long): Long = {
      var total = 0L
      for (d <- 1 to 9) {
        if (DEBUG) {
          println(d)
        }
        total += f(0, d, 2)
      }
      total
    }

//    time(calcFirst(calc1))
//    time(calcFirst(calc2))
    time{
      for {
        a <- 0 to 9
        b <- 0 to 9
      } memo(a)(b) = calc2(a, b, MAX_INDEX - SP_MAX)
      calcFirst(calc3)
    }

    //10 =    21838806
    //12 =   612836369
    //13 =  3243500668
    //14 = 17173865103

    //20 ?= 378158756814587
  }
}
