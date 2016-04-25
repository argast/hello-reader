package pfws

import pfws.ReaderDirectives._
import spray.routing.Directives._
import spray.routing._

import scala.concurrent.ExecutionContext.Implicits.global
import scalaz.Scalaz._
import scalaz._
import scalaz.concurrent.{Future => ZFuture}

object HelloRoutes {

  def routes(implicit c: Config): Route = {
    path("greeting") {
      completeReader {
        "Hello"
      }
    } ~ path("hello") {
      completeReader {
        GreetingService.getGreeting.map(_ + " world!")
      }
    }
  }
}
