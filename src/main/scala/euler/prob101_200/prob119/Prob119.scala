package euler.prob101_200.prob119

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob119 extends Util {

  def main(args: Array[String]): Unit = {

    val v = NATURALS.takeWhile(_ < 500).flatMap(n => {
      (1 to 100).map(exp => BigInt(n).pow(exp)).filter(number => number.sumDigits == n)
    }).filter(_ >= 10)

    val list = v.toList.sorted

    println(list.size)

    result(list.drop(29).head)

  }

}
