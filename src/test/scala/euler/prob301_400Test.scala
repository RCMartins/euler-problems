package euler

import euler.prob301_400._
import euler.traits.UtilResult

class prob301_400Test extends EulerTestingSuite {

  val problems: Map[Int, UtilResult] =
    List(
      (345, Prob345)
    ).toMap

  testProblem(345, 13938L)

}
