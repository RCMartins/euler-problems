package euler.prob001_100

import euler.traits.UtilResult

/** Created by Ricardo
  */
object Prob011 extends UtilResult {
  def sumOf4(
      data: Array[Array[Int]]
  )(pos: (Int, Int), f: ((Int, Int)) => (Int, Int)): Option[Int] = {
    def sumOf4Aux(pos: (Int, Int), posLeft: Int): Option[Int] = {
      if (posLeft == 0) Some(1)
      else {
        val (x, y) = pos
        if (y >= 0 && y < data.length && x >= 0 && x < data(y).length) {
          sumOf4Aux(f(pos), posLeft - 1) match {
            case Some(n) => Some(n * data(y)(x))
            case None    => None
          }
        } else
          None
      }
    }
    sumOf4Aux(pos, 4)
  }

  override def calc: Long = {
    val data: Array[Array[Int]] =
      readData("p011_data.txt").split('\n').map(_.split(' ').map(_.toInt))

    val f = sumOf4(data) _

    val values = for {
      x <- 0 to 19
      y <- 0 to 19
    } yield Vector(
      f((x, y), { case (x, y) => (x + 1, y) }),
      f((x, y), { case (x, y) => (x, y + 1) }),
      f((x, y), { case (x, y) => (x + 1, y + 1) }),
      f((x, y), { case (x, y) => (x + 1, y - 1) })
    )

    values.flatten
      .map {
        case Some(x) => x
        case None    => 0
      }
      .sorted
      .reverse
      .head
  }
}
