import spray.httpx.marshalling.{Marshaller, ToResponseMarshallable, ToResponseMarshaller, ToResponseMarshallingContext}

import scalaz._
import Scalaz._
import scala.concurrent.Future


package object pfws {

  type ReaderMarshaller[T] = ToResponseMarshaller[Reader[Config, T]]

  implicit def pure[T](v: T): Reader[Config, T] = Reader { _ => v }

  implicit def liftToFuture[T](r: Reader[Config, T]): ReaderT[Future, Config, T] = ReaderT(config => Future.successful(r(config)))

  //  def readerIsMarshallableWithConfig[T](config: Config)(implicit m: ToResponseMarshaller[T]): ReaderMarshaller[T] = {
//    new ToResponseMarshaller[Reader[Config, T]] {
//      override def apply(value: Reader[Config, T], ctx: ToResponseMarshallingContext): Unit = {
//        m(value(config), ctx)
//      }
//    }
//  }
}
