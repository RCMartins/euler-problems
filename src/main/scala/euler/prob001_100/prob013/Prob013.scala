package euler.prob001_100.prob013

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob013 extends UtilResult {
  def calc: Long = {
    val data = readFile("data\\p013_data.txt").split('\n').map(BigInt(_))

    data.sum.toString.substring(0, 10).toLong
  }
}
