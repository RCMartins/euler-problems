package euler.prob101_200.prob108

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob108 extends Util {

  def main(args: Array[String]): Unit = {

    val n = 6
    println(Stream.from(n + 1).map(a => {
      (a, 1.0 / ((1.0 / n) - (1.0 / a)))
    }).map { case (a, b) => (a,b) }.takeWhile(pair => pair._2 >= pair._1).force)

  }

}
