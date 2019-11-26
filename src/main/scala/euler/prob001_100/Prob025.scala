package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob025 extends UtilResult {
  def calc: Long = {
    var index = 2
    var a = BigInt(1)
    var b = BigInt(2)
    while (a.toString.length < 1000) {
      val c = a + b
      a = b
      b = c
      index += 1
    }

    index
  }
}
