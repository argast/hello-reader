import akka.actor.ActorSystem
import au.com.dius.pact.consumer.{ConsumerPactBuilder, ConsumerPactRunner, PactVerified, TestRun}
import org.scalatest.{FunSpec, Matchers}
import pfws._
import spray.http.StatusCodes
import spray.httpx.marshalling.{Marshaller, ToResponseMarshallable, ToResponseMarshaller}
import spray.testkit.ScalatestRouteTest

import scalaz._
import au.com.dius.pact.consumer.ConsumerPactBuilder._
import au.com.dius.pact.model.{Consumer, MockProviderConfig, PactFragmentBuilder}


class RoutesTest extends FunSpec with ScalatestRouteTest with Matchers {

  val mock = MockProviderConfig.createDefault()

  implicit val config = new TestConfig {
    override val greetingUrl: String = mock.url
  }

  val noop = (_: Unit) => None

  describe("Hello routes") {
    it("should return correct response") {

      val pact = PactFragmentBuilder(new Consumer("greeting"))
        .hasPactWith("hello")
        .uponReceiving("a greeting request")
        .matching("/greeting")
        .willRespondWith(body = "Hello")
        .duringConsumerSpec(mock) ({
          Get("/hello") ~> HelloRoutes.routes ~> check {
            status should equal (StatusCodes.OK)
            body.asString should equal ("Hello| world!")
          }
        }, noop)

      pact should equal (PactVerified)
    }
  }
}
