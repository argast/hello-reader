import akka.actor.ActorSystem
import akka.io.IO
import spray.can.Http
import spray.can.Http.Bind

object Main extends App {

  implicit val actorSystem = ActorSystem("system")

  val config = new Config {
    override val system = actorSystem
  }

  val httpConnection = actorSystem.actorOf(HttpConnectionActor.props(
    CalculatorRoutes.routes(config)
  ))

  IO(Http) ! Bind(listener = httpConnection, interface = "0.0.0.0", port = 9090)
}
