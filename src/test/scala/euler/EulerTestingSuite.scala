package euler

import euler.traits.UtilResult
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

trait EulerTestingSuite extends AnyWordSpec with Matchers {

  UtilResult.showPrints = false

  val problems: Map[Int, UtilResult]

  def testProblem(problemNumber: Int, expected: Long, ignored: Boolean = false): Unit = {
    val testName = "Problem %03d should return %d".format(problemNumber, expected)
    if (ignored)
      testName ignore ()
    else
      testName in {
        val (result, time) = problems(problemNumber).calcWithTime
        println(s"time: $time")
        assert(result == expected)
      }
  }

}
