package pfws

import pfws.ReaderDirectives._
import spray.routing.Directives._
import spray.routing._

import scala.concurrent.ExecutionContext.Implicits.global
import scalaz._
import Scalaz._
import GreetingService._

object HelloRoutes {

  def routes(implicit c: Config): Route = {
    path("greeting") {
      completeReader {
        "Hello"
      }
    } ~ path("hello") {
      completeReader {
        for {
          greeting <- getGreeting
          separator <- Database.get("separator")
        } yield greeting + separator + "world!"
      }
    }
  }
}
