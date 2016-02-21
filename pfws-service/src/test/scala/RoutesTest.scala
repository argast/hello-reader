import akka.actor.ActorSystem
import org.scalatest.{Matchers, FunSpec}
import spray.http.StatusCodes
import spray.testkit.ScalatestRouteTest

class RoutesTest extends FunSpec with ScalatestRouteTest with Matchers {

  val config = new Config {
    override implicit val system: ActorSystem = ActorSystem()
  }

  describe("Hello routes") {
    it("should return correct response") {
      Get("/hello") ~> CalculatorRoutes.routes(config) ~> check {
        status should equal (StatusCodes.OK)
      }
    }
  }

}
