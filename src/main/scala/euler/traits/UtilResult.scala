package euler.traits

trait UtilResult extends Util {

  def calc: Long = 0L

  def calcBigDecimal: BigDecimal = BigDecimal(0)

  def main(args: Array[String]): Unit = {
    time(Some(calc).filter(_ != 0L).getOrElse(calcBigDecimal))
  }

  def calcWithTime: (Any, Long) = {
    val t = System.currentTimeMillis()
    val res = Some(calc).filter(_ != 0L).getOrElse(calcBigDecimal)
    val totalTime = System.currentTimeMillis() - t
    (res, totalTime)
  }

  def println(): Unit =
    if (UtilResult.showPrints) _root_.scala.Predef.println()

  def println(any: => Any): Unit =
    if (UtilResult.showPrints) _root_.scala.Predef.println(any)
}

object UtilResult {
  var showPrints: Boolean = true
}
