package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob067 extends UtilResult {
  def calc: Long = {
    val data = readData("p067_triangle.txt").split("\n")
        .map(_.split(" ").map(_.toInt))

    val SIZE = data.length

    val v = (SIZE - 2 to 0 by -1).foldLeft(data(SIZE - 1))((acc: Array[Int], line) => {
      (0 to line).map(index => data(line)(index) + math.max(acc(index), acc(index + 1))).toArray
    })

    v.head
  }
}
