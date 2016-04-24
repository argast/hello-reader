package pfws

import pfws.ReaderDirectives._
import spray.routing.Directives._
import spray.routing._

import scala.concurrent.ExecutionContext.Implicits.global
import scalaz.Scalaz._
import scalaz._
import scalaz.concurrent.{Future => ZFuture}

object HelloRoutes {

  implicit def pure[T](v: T): Reader[Config, T] = Reader { _ => v }

  def routes(implicit c: Config): Route = {
    path("greeting") {
      completeReader {
          Reader { _ => "Hello" }
      }
    } ~ path("hello") {
      completeReader {
        for {
          greeting <- GreetingService.getGreeting
        } yield greeting + " world!"
      }
    }
  }
}
