package pfws

import akka.actor.ActorSystem
import akka.pattern.CircuitBreaker

import spray.client.pipelining._
import scala.concurrent.ExecutionContext.Implicits.global

trait Config {

  val port = 9090

  implicit val actorSystem = ActorSystem("hello-reader")

  val database = Map[String, String]("separator" -> ", ")

  lazy val pipeline = sendReceive

  val greetingUrl = "http://localhost:9090"
}
