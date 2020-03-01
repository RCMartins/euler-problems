package euler.prob101_200.prob141

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob141 extends Util {

  def main(args: Array[String]): Unit = {

    val MaxValue = 10e12.toLong

    val squares = Stream.from(1).map(n => n.toLong * n).takeWhile(_ < MaxValue)

    println(squares.mkString("\n"))
    println(MaxValue)
    println(squares.size)

  }

}
