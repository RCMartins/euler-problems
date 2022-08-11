package euler

import euler.prob401_500._
import euler.traits.UtilResult

class prob401_500Test extends EulerTestingSuite {

  val problems: Map[Int, UtilResult] =
    List(
      (493, Prob493),
      (500, Prob500),
    ).toMap

  testProblem(493, 6818741802L)
  testProblem(500, 35407281L)

}
