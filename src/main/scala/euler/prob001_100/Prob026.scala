package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob026 extends UtilResult {
  override def calc: Long = {
    val tries: Int = 4

    def testSize(numberStr: Vector[Char]): Int = {
      def testSizeAux(size: Int): Option[Int] = {
        if ((tries + 1) * size > numberStr.length)
          None
        else if ((0 until tries)
                   .map(t => numberStr.slice(t * size, (t + 1) * size))
                   .toSet
                   .size == 1)
          Some(size)
        else
          testSizeAux(size + 1)
      }
      numberStr.length match {
        case 0 => 0
        case _ =>
          testSizeAux(1) match {
            case None        => testSize(numberStr.tail)
            case Some(value) => value
          }
      }
    }

    val mathContext = new java.math.MathContext(5000)

    val v = for {
      denom <- 2 until 1000
      number = BigDecimal(1, mathContext) / denom
    } yield {
      (denom, number.toDouble, testSize(number.toString.toVector.drop(2)))
    }

    v.sortBy(_._3).reverse.head._1
  }
}
