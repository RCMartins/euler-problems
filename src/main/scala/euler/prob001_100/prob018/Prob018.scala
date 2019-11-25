package euler.prob001_100.prob018

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob018 extends UtilResult {
  def calc: Long = {
    val data = readFile("data\\p018_data.txt").split("\n").map(_.split(" ").map(_.toInt))

    val size = data.length

    val v = (size - 2 to 0 by -1).foldLeft(data(size - 1))((acc: Array[Int], line) => {
      (0 to line).map(index => data(line)(index) + math.max(acc(index), acc(index + 1))).toArray
    })

    v.head
  }
}
