package pfws


import akka.actor.Props
import spray.routing.{HttpServiceActor, Route}


class HttpConnectionActor(routes: Route*) extends HttpServiceActor {

  override def receive: Receive = runRoute(routes.reduce(_ ~ _))
}

object HttpConnectionActor {

  def props(routes: Route*) = Props(classOf[HttpConnectionActor], routes)
}