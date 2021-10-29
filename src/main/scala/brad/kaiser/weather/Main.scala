package brad.kaiser.weather

import brad.kaiser.weather.config.Config
import brad.kaiser.weather.routes.WeatherRoutes
import brad.kaiser.weather.services.OpenWeatherService
import cats.syntax.all._
import cats.effect._
import org.http4s.blaze.server._
import org.http4s.implicits._
import org.http4s._
import org.http4s.blaze.client.BlazeClientBuilder
import org.http4s.dsl.io._
import org.http4s.server.{Router, Server}
import pureconfig._
import pureconfig.generic.auto._



import scala.concurrent.ExecutionContext.Implicits.global

/* Todo
rename application.conf for defaults
move client out of request
add more tests
add logs
rearrange initializations
 */

object Main extends IOApp {

  def run(args: List[String]): IO[ExitCode] = {
    println("hello")
    val config: Config = ConfigSource.default.load[Config].getOrElse(throw new IllegalArgumentException("died"))

    val server = for {
      client <- BlazeClientBuilder[IO].resource
      openWeatherService = OpenWeatherService(config.openWeather, client)
      router = Router("/" -> WeatherRoutes.routes(openWeatherService)).orNotFound
      server <- BlazeServerBuilder[IO]
          .bindHttp(8080, "localhost")
          .withHttpApp(router)
        .resource
    } yield server

    server.use(_ => IO.never).as(ExitCode.Success)


    //    val openWeatherService = OpenWeatherService(config.openWeather)
//
//    println(config)
//
//
//    val router = Router("/" -> WeatherRoutes.routes(openWeatherService)).orNotFound

//    BlazeServerBuilder[IO]
//      .bindHttp(8080, "localhost")
//      .withHttpApp(router)
//      .serve
//      .compile
//      .drain
//      .as(ExitCode.Success)
  }
}
