package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob008 extends UtilResult {
  def calc: Long = {
    val data = readData("p008_data.txt").filter(_.isDigit)
    val SeqSize: Int = 13

    val indices = 0 to data.length - SeqSize
    val mapped = indices.map(
      index => (index until index + SeqSize).foldLeft(1L)((total, i) => (data(i) - '0') * total)
    )
    val sorted = mapped.zip(indices).sortWith((a, b) => a._1 > b._1)

    sorted.head._1
  }
}
