package pfws

import scalaz.Scalaz._
import scalaz.{EitherT, Reader, _}

/**
  * Created by pawel on 16/04/2016.
  */
object Test extends App {

  type Result = String \/ Int
  type AWriter = Writer[String, Result]
  type WriterType[A] = Writer[String, A]
  type AEither[A] = EitherT[WriterType, String, A]
  type AReader[A] = ReaderT[AEither, String, A]

  EitherT

  val w: AWriter = 1.right[String].set("")
  val ww = 1.right[String].set("")
  val e = EitherT[WriterType, String, Int](ww)

  val rr: AReader[Int] = ReaderT[AEither, String, Int] { s =>
    EitherT[WriterType, String, Int](ww)
  }

  import Database._
  val a = get("aa")

  val read: Reader[Config, Int] = for {
    s <- get("")
  } yield s.toInt


  val aaa = for {
    aa <- rr
  } yield aa

  aaa("aa")

  val b = for {
    a <- e
  } yield a

  println(b.map(_+1))

  val r = Reader[String, List[Int]] { s => s.split(",").map(_.toInt).toList }
  val rt = ReaderT[Option, String, List[Int]] { s => s.split(",").map(_.toInt).toList.some }

  class Repository {
    val getItems = Reader[String, List[Int]] { s => s.split(",").map(_.toInt).toList }
  }

  class Service {
    val calculate = (l: List[Int]) => l.sum
  }

//  val repo = new Repository
//  val service = new Service
//
//  val bb = for {
//    aa <- rt
//  } yield aa
//  val cc = bb("1,2")
//  println(cc)

//  val r3 = for {
//    s <- getItems
//  } yield calculate(s)
//
//  println(r3("1,2,3,4"))

//  def contents(root: File): List[File] = {
//    if (root.isDirectory) root :: root.listFiles().toList.flatMap(contents)
//    else List(root)
//  }
//
//  def count[S](root: File, f: (File => S))(implicit sem: Semigroup[S]): S = {
//    contents(root).map(f).reduce(_ |+| _)
//  }
//
//  val files = (f: File) => if (f.isFile) 1 else 0
//  val lines = (f: File) => scala.io.Source.fromFile(f).getLines().size
//
//  println(count(new File("/Users/pawel/Downloads"), files))
}
