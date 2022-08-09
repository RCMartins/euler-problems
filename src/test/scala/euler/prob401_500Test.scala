package euler

import euler.prob401_500._
import euler.traits.UtilResult

class prob401_500Test extends EulerTestingSuite {

  val problems: Map[Int, UtilResult] =
    List(
      (500, Prob500)
    ).toMap

  testProblem(500, 35407281L)

}
