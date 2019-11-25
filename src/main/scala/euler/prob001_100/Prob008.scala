package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob008 extends UtilResult {
  def calc: Long = {
    val DATA = readData("p008_data.txt").filter(_.isDigit)
    val SEQ_SIZE: Int = 13

    val indices = 0 to DATA.length - SEQ_SIZE
    val mapped = indices.map(
      index => (index until index + SEQ_SIZE).foldLeft(1L)((total, i) => (DATA(i) - '0') * total)
    )
    val sorted = mapped.zip(indices).sortWith((a, b) => a._1 > b._1)

    sorted.head._1
  }
}
