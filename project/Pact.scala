import au.com.dius.pact.provider.sbt.{ProviderConfig, SbtProviderPlugin}
import au.com.dius.pact.provider.sbt.SbtProviderPlugin._
import sbt._

object Pact {

  val pactSettings = SbtProviderPlugin.config ++ Seq(
    providers := Seq(
      ProviderConfig(host= "localhost", port = 9090, name = "hello")
        .hasPactsInDirectory(file("target/pacts"))
    ))
}