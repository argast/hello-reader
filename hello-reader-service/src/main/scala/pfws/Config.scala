package pfws

import akka.actor.ActorSystem
import akka.pattern.CircuitBreaker

import spray.client.pipelining._
import scala.concurrent.ExecutionContext.Implicits.global

trait Config {

  implicit val system: ActorSystem

  val mongo = Map[String, String]("aa" -> "Hello")

  lazy val pipeline = sendReceive

  val greetingUrl = "http://localhost:9090"
}
