package pfws

import akka.actor.ActorSystem
import akka.io.IO
import pfws.HttpConnectionActor.{Start, Stop}
import spray.can.Http
import spray.can.Http.Bind
import spray.http.HttpEntity
import spray.httpx.marshalling.{Marshaller, ToResponseMarshallable, ToResponseMarshaller, ToResponseMarshallingContext}

import scalaz._

object Main extends App {


  implicit val config = new Config {
  }

  import config._


 // implicit def readerIsMarshallable[T](r: Reader[Config, T])(implicit m: Marshaller[T]): ToResponseMarshallable = r(config)

 // implicit def readerIsMarshallable[T](implicit m: ToResponseMarshaller[T]): ReaderMarshaller[T] = readerIsMarshallableWithConfig(config)

  val http = actorSystem.actorOf(HttpConnectionActor.props(
    HelloRoutes.routes
  ))

  http ! Start

  sys.addShutdownHook {
    http ! Stop
  }


}
