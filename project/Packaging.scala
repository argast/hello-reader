import com.typesafe.sbt.SbtNativePackager._
import com.typesafe.sbt.packager.Keys._
import sbt.Keys._
import sbt._

object Packaging {

  val packagingSettings = Seq(
    mainClass in Compile := Some("Main")
    //dockerExposedPorts in Docker := Seq(9090, 9090),
    //dockerExposedVolumes in Docker := Seq("/opt/docker/logs")
  )
}