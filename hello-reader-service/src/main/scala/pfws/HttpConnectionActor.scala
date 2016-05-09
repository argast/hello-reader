package pfws


import akka.actor.{ActorRef, ActorSystem, Props}
import akka.io.IO
import pfws.HttpConnectionActor.{Start, Stop}
import spray.can.Http
import spray.can.Http.{Bind, Unbind}
import spray.routing.{HttpServiceActor, Route}


class HttpConnectionActor(routes: Route*)(implicit as: ActorSystem, c: Config) extends HttpServiceActor {

  val handleRequest: Receive = runRoute(routes.reduce(_ ~ _))

  override def receive: Receive = {
    case Start => IO(Http) ! Bind(listener = self, interface = "0.0.0.0", port = c.port)
      context.become(starting)
  }

  def starting: Receive = {
    case Http.Bound(address) => context.become(started(sender))
  }

  def started(boundActor: ActorRef): Receive = stop(boundActor).orElse(handleRequest)

  def stop(boundActor: ActorRef): Receive = {
    case Stop => boundActor ! Unbind
      context.become(stopping(sender))
  }

  def stopping(sender: ActorRef): Receive = {
    case m @ Http.Unbound => sender ! m
  }

}

object HttpConnectionActor {

  case object Start
  case object Stop

  def props(routes: Route*)(implicit as: ActorSystem, c: Config) = Props(new HttpConnectionActor(routes: _*))
}