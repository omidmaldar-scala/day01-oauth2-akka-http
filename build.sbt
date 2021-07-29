import Dependencies._

ThisBuild / scalaVersion     := "2.13.6"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "com.dive-into-scala"
ThisBuild / organizationName := "dive-into-scala"

lazy val root = (project in file("."))
  .settings(
    name := "Day01-oauth2-akka",
    libraryDependencies += scalaTest % Test
  )

