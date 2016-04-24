import sbt.Keys._
import sbt._

object Dependencies {

  val sprayVersion = "1.3.3"
  val scalazVersion = "7.2.0"

  val commonDependencies = Seq(
    "ch.qos.logback" % "logback-classic" % "1.1.7",
    "org.slf4j" % "slf4j-api" % "1.7.21",
    "org.scalatest" %% "scalatest" % "2.2.4" % "test",
    "org.scalaz" %% "scalaz-core" % scalazVersion
  )

  val serviceDependencies = deps(
    commonDependencies ++ Seq(
    "io.spray" %% "spray-can" % sprayVersion,
    "io.spray" %% "spray-routing" % sprayVersion,
    "io.spray" %% "spray-client" % sprayVersion,
    "org.json4s" %% "json4s-jackson" % "3.3.0",
    "com.typesafe.akka" %% "akka-actor" % "2.4.1",
    "org.typelevel" %% "scalaz-contrib-210"  % "0.2",
    "org.scalaz" %% "scalaz-concurrent" % scalazVersion,
    "io.spray" %% "spray-testkit" % sprayVersion % "test",
    "au.com.dius" %% "pact-jvm-consumer" % "3.2.2" % "test"
  ))

  val integrationTestsDependencies = deps(
    commonDependencies ++ Seq(
      "com.jayway.restassured" % "rest-assured" % "2.8.0"
    ))

  val performanceTestsDependencies = deps(
    commonDependencies ++ Seq(
      "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.1.7"
    ))

  private def deps(modules: Seq[ModuleID]): Seq[Setting[_]] = Seq(libraryDependencies ++= modules)
}