package euler

import euler.traits.UtilResult
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

trait EulerTestingSuite extends AnyWordSpec with Matchers {

  UtilResult.showPrints = false

}
