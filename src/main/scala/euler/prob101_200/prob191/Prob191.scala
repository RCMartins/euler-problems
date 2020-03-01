package euler.prob101_200.prob191

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob191 extends Util {

  def main(args: Array[String]): Unit = {

    time {
      val MaxDays = 30

      val array = Array.ofDim[Long](MaxDays + 1, 2, 3)
      for {
        day <- 0 to MaxDays
        late <- 0 to 1
        absent <- 0 to 2
      } {
        array(day)(late)(absent) = -1
      }

      def loop(day: Int, late: Int, absent: Int): Long = {
        if (array(day)(late)(absent) == -1) {
          array(day)(late)(absent) = {
            if (day == 0)
              1
            else {
              (if (absent > 0) loop(day - 1, late, absent - 1) else 0) +
                (if (late > 0) loop(day - 1, 0, 2) else 0) +
                loop(day - 1, late, 2)
            }
          }
        }
        array(day)(late)(absent)
      }

      loop(MaxDays, 1, 2)
    }
  }

}
