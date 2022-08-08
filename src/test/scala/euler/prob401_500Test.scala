package euler

import euler.prob401_500._
import euler.traits.UtilResult

class prob401_500Test extends EulerTestingSuite {

  private val problems: Map[Int, UtilResult] =
    List(
      (500, Prob500)
    ).toMap

  def testProblem(problemNumber: Int, expected: Long): Unit = {
    "Problem %03d should return %d".format(problemNumber, expected) in {
      val (result, time) = problems(problemNumber).calcWithTime
      println(s"time: $time")
      assert(result == expected)
    }
  }

  testProblem(500, 35407281L)
}
