import sbt._

object Versions {
  val AkkaHttp = "10.2.5"
  val AkkaStream = "2.6.8"
  val AkkaHttpJSON4s = "1.36.0"
  val JSON4s = "4.0.3"
  val ScalaLogging = "3.9.4"
  val Slf4j = "1.7.32"
}

object Dependencies {
  
  private val akkaHttp = Seq(
    "com.typesafe.akka" %% "akka-http" % Versions.AkkaHttp
  )

  private val akkStream = Seq(
    "com.typesafe.akka" %% "akka-stream" % Versions.AkkaStream
  )

  private val akkaHttpJSON = Seq(
    "de.heikoseeberger" %% "akka-http-json4s" % Versions.AkkaHttpJSON4s
  )

  private val json4s = Seq(
    "org.json4s" %% "json4s-native" % Versions.JSON4s
  )

  private val scalaLogging = Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % Versions.ScalaLogging
  )

  private val slf4j = Seq(
    "org.slf4j" % "slf4j-simple" % Versions.Slf4j % Test
  )
  

  val appDependencies = akkaHttp ++ akkStream ++ akkaHttpJSON ++ json4s ++ scalaLogging ++ slf4j
}
