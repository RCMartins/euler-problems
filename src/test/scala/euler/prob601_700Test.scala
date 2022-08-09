package euler

import euler.prob601_700._
import euler.traits.UtilResult

class prob601_700Test extends EulerTestingSuite {

  val problems: Map[Int, UtilResult] =
    List(
      (700, Prob700)
    ).toMap

  testProblem(700, 1517926517777556L)

}
