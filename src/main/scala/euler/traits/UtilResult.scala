package euler.traits

trait UtilResult extends Util {
  def calc: Long

  def main(args: Array[String]): Unit = {
    time(calc)
  }

  def calcWithTime: (Long, Long) = {
    val t = System.currentTimeMillis()
    val res = calc
    val totalTime = System.currentTimeMillis() - t
    (res, totalTime)
  }

  def println(any: => Any): Unit =
    if (UtilResult.showPrints) _root_.scala.Predef.println(any)
}

object UtilResult {
  var showPrints: Boolean = true
}
