name := "WeatherService"

version := "0.1"

scalaVersion := "2.13.6"

val http4sVersion = "0.23.6"

libraryDependencies ++= Seq(
  "com.github.pureconfig" %% "pureconfig" % "0.17.0",
  "io.circe" %% "circe-generic" % "0.14.1",
  "io.circe" %% "circe-literal" % "0.14.1",
  "io.circe" %% "circe-parser" % "0.14.1",
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,
  "org.slf4j" % "slf4j-api" % "1.7.32",
  "org.slf4j" % "slf4j-simple" % "1.7.32",
  "org.typelevel" %% "cats-core" % "2.6.1",
  "org.typelevel" %% "cats-effect" % "3.2.8",
  "org.typelevel" %% "cats-kernel" % "2.6.1",
  "org.scalatest" %% "scalatest" % "3.2.10" % "test"
)
