import CommonSettings._
import Dependencies._
import Packaging._

lazy val root = (project in file("."))
  .aggregate(`pfws-service`, `pfws-integration-tests`, `pfws-performance-tests`)

lazy val `pfws-service` = (project in file("pfws-service"))
  .enablePlugins(JavaServerAppPackaging, DockerPlugin)
  .settings(packagingSettings: _*)
  .settings(commonSettings: _*)
  .settings(serviceDependencies: _*)

lazy val `pfws-integration-tests` = (project in file("pfws-integration-tests"))
  .settings(commonSettings: _*)
  .settings(integrationTestsDependencies: _*)

lazy val `pfws-performance-tests` = (project in file("pfws-performance-tests"))
  .settings(commonSettings: _*)
  .settings(performanceTestsDependencies: _*)