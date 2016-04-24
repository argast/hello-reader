package pfws

import scalaz._
import Scalaz._
import spray.client.pipelining._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

object GreetingService {

  def getGreeting: ReaderT[Future, Config, String] = ReaderT { config =>
    val p = config.pipeline ~> unmarshal[String]
    p(Get(config.greetingUrl + "/greeting"))
  }

}
