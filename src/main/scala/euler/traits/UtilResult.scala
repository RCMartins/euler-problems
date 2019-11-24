package euler.traits

trait UtilResult extends Util {

  def calc: Long

  def main(args: Array[String]): Unit = {
    time(calc)
  }

}
