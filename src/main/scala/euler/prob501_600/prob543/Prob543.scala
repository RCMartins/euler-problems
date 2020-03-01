package euler.prob501_600.prob543

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob543 extends Util {

  def main(args: Array[String]): Unit = {

    val fib = 1 #:: ALL_FIB.take(44)

    println(fib.take(44).toList)

  }

}
