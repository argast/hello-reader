

import org.json4s.DefaultFormats
import spray.httpx.Json4sJacksonSupport
import spray.routing.Directives._
import spray.routing.Route

import scalaz.Reader

object CalculatorRoutes extends Json4sJacksonSupport {

  override implicit def json4sJacksonFormats = DefaultFormats

  def routes: Reader[Config, Route] = Reader { config =>
    path("hello") {
        complete {
          Hello(s"Hello")
        }
      }
    }
}
