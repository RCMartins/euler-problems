package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob015 extends UtilResult {
  override def calc: Long = {
    val field: Array[Array[Long]] = Array.ofDim(21, 21)

    (0 to 20).foreach(x => {
      field(0)(x) = 1
      field(x)(0) = 1
    })

    for {
      y <- 1 to 20
      x <- 1 to 20
    } {
      field(x)(y) = field(x - 1)(y) + field(x)(y - 1)
    }

    field(20)(20)
  }
}
