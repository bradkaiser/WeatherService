package brad.kaiser.weather

import brad.kaiser.weather.config.Config
import brad.kaiser.weather.routes.WeatherRoutes
import brad.kaiser.weather.services.OpenWeatherService
import cats.effect._
import org.http4s.blaze.server._
import org.http4s.implicits._
import org.http4s.blaze.client.BlazeClientBuilder
import org.http4s.server.Router
import pureconfig._
import pureconfig.generic.auto._

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    val config: Config = ConfigSource.default.load[Config].getOrElse(throw new IllegalArgumentException("died"))

    val server = for {
      client <- BlazeClientBuilder[IO].resource
      openWeatherService = OpenWeatherService(config.openWeather, client)
      router = Router("" -> WeatherRoutes.routes(openWeatherService)).orNotFound
      server <- BlazeServerBuilder[IO]
          .bindHttp(8080, "localhost")
          .withHttpApp(router)
        .resource
    } yield server

    server.use(_ => IO.never).as(ExitCode.Success)
  }
}
