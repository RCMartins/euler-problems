package euler.prob501_600.prob580

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob580 extends Util {

  def main(args: Array[String]): Unit = {

    val MAX = 1e7.toLong
    println(MAX)

    //    println(LCM(25, 81))

    val hilberts = LazyList.from(1).map(k => 4L * k + 1).map(n => n * n).takeWhile { n => /*println(n);*/ n <= MAX }

    //  1e07 = 790
    //  1e16 = 24999999
    println(hilberts.size)

    val result = hilberts.take(5000).foldLeft(List(1L))((list, hilbert) => list.map(_ * hilbert).filter(_ <= MAX) ++ list)

    println(result.size)
    println(MAX - result.size)

  }

}
