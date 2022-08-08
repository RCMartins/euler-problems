package euler.prob201_300.prob225

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob225 extends Util {

  def main(args: Array[String]): Unit = {

    def calc(a: Int, b: Int, c: Int): LazyList[Long] = {
      val n = a + b + c
      n #:: calc(b, c, n)
    }

    val tribonacci = 1L #:: calc(1, 1, 1)

    println(tribonacci.take(20).force)

  }

}
