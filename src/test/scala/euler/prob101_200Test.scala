package euler

import euler.prob101_200._
import euler.traits.UtilResult

class prob101_200Test extends EulerTestingSuite {

  val problems: Map[Int, UtilResult] =
    List(
      (108, Prob108)
    ).toMap

  testProblem(108, 180180L, ignored = true)
}
