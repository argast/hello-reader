package pfws

import scala.concurrent.Future
import scalaz.{Reader, ReaderT}
import scala.concurrent.ExecutionContext.Implicits.global

object Database {

  def get(key: String): Reader[Config, String] =  Reader { config =>
    config.database(key)
  }

  def getDefault(key: String, default: String): ReaderT[Future, Config, String] = ReaderT { config =>
    Future {
      config.database.getOrElse(key, default)
    }
  }


}
