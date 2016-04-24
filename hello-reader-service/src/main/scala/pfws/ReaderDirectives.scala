package pfws

import spray.httpx.marshalling.ToResponseMarshaller
import spray.routing.Directives._
import spray.routing._

import scalaz._

object ReaderDirectives {

  def completeReader[F[_], T](reader: ReaderT[F, Config, T])(implicit m: ToResponseMarshaller[F[T]], c: Config): Route = complete {
    reader(c)
  }

}
