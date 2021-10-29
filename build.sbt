name := "WeatherService"

version := "0.1"

scalaVersion := "2.13.6"

idePackagePrefix := Some("brad.kaiser")

val http4sVersion = "0.21.30"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion
)