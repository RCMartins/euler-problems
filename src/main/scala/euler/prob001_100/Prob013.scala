package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob013 extends UtilResult {
  override def calc: Long = {
    val data = readData("p013_data.txt").split('\n').map(BigInt(_))

    data.sum.toString.substring(0, 10).toLong
  }
}
