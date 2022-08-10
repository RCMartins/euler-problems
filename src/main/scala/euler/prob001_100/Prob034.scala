package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob034 extends UtilResult {
  def calc: Long = {
    val expDigit = 1 +: (1 to 9).map(n => (1 to n).product).toVector

    class NumberElement(val number: BigInt, val sumOfFact: BigInt) extends Ordered[NumberElement] {
      private[NumberElement] def v: NumberElement => BigInt =
        (e: NumberElement) => -(e.number - e.sumOfFact).abs

      val BIG_MAX = BigInt(Int.MaxValue)
      val BIG_MIN = BigInt(Int.MinValue)

      def compare(that: NumberElement): Int = {
        val value = v(this) - v(that)
        if (value > BIG_MAX)
          BIG_MAX.toInt
        else if (value < BIG_MIN)
          BIG_MIN.toInt
        else
          value.toInt
      }

      def isCurious: Boolean = number == sumOfFact

      override def toString: String = (number.toString, sumOfFact.toString).toString
    }

    val BIGINTN = (0 to 10).map(BigInt(_))

    val queue = collection.mutable.PriorityQueue[NumberElement]()

    queue.++=((1 to 9).map(n => new NumberElement(BigInt(n), BigInt(expDigit(n)))))

    var foundNumbers = List[Int]()

    var counter = 1L
    while (queue.nonEmpty) {
      val next: NumberElement = queue.dequeue()
      if (next.isCurious && next.number >= 10) {
        foundNumbers = next.number.toInt :: foundNumbers
      }
      counter += 1L
      if (counter > 50000)
        return foundNumbers.sum

      for (j <- 0 to 9) {
        queue += new NumberElement(
          next.number * BIGINTN(10) + BIGINTN(j),
          next.sumOfFact + expDigit(j)
        )
      }
    }
    ???
  }
}
