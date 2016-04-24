package pfws

import akka.actor.ActorSystem
import akka.io.IO
import spray.can.Http
import spray.can.Http.Bind
import spray.http.HttpEntity
import spray.httpx.marshalling.{Marshaller, ToResponseMarshallable, ToResponseMarshaller, ToResponseMarshallingContext}

import scalaz._

/**
  * Created by pawel on 16/04/2016.
  */
object Main extends App {


  implicit val actorSystem = ActorSystem("system")

  implicit val config = new Config {
    override val system = actorSystem
  }

 // implicit def readerIsMarshallable[T](r: Reader[Config, T])(implicit m: Marshaller[T]): ToResponseMarshallable = r(config)

 // implicit def readerIsMarshallable[T](implicit m: ToResponseMarshaller[T]): ReaderMarshaller[T] = readerIsMarshallableWithConfig(config)

  val httpConnection = actorSystem.actorOf(HttpConnectionActor.props(
    HelloRoutes.routes
  ))

  IO(Http) ! Bind(listener = httpConnection, interface = "0.0.0.0", port = 9090)
}
