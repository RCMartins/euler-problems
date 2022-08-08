package euler

import euler.prob101_200.Prob108
import euler.traits.UtilResult

class prob101_200Test extends EulerTestingSuite {

  private val problems: Map[Int, UtilResult] =
    List(
      (108, Prob108)
    ).toMap

  def testProblem(problemNumber: Int, expected: Long): Unit = {
    "Problem %03d should return %d".format(problemNumber, expected) in {
      val (result, time) = problems(problemNumber).calcWithTime
      println(s"time: $time")
      assert(result == expected)
    }
  }

  testProblem(108, 180180L)
}
