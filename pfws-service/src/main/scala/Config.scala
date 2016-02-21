import akka.actor.ActorSystem
import akka.pattern.CircuitBreaker

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  *
  * Created by pawel on 30/01/2016.
  */
trait Config {

  implicit val system: ActorSystem

}
