import CommonSettings._
import Dependencies._
import Packaging._
import Pact._

lazy val root = (project in file("."))
  .aggregate(`hello-reader-service`, `hello-reader-integration-tests`, `hello-reader-performance-tests`)
  .settings(Pact.pactSettings: _*)

lazy val `hello-reader-service` = (project in file("hello-reader-service"))
  .enablePlugins(JavaServerAppPackaging, DockerPlugin)
  .settings(packagingSettings: _*)
  .settings(commonSettings: _*)
  .settings(serviceDependencies: _*)

lazy val `hello-reader-integration-tests` = (project in file("hello-reader-integration-tests"))
  .settings(commonSettings: _*)
  .settings(integrationTestsDependencies: _*)

lazy val `hello-reader-performance-tests` = (project in file("hello-reader-performance-tests"))
  .settings(commonSettings: _*)
  .settings(performanceTestsDependencies: _*)