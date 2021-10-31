package brad.kaiser.weather.routes

import brad.kaiser.weather.models.WeatherServiceResponse
import brad.kaiser.weather.services.OpenWeatherService
import cats.Functor
import cats.effect._
import org.http4s._
import org.http4s.dsl.io._
import io.circe.generic.auto._
import org.http4s.circe.jsonEncoderOf
import org.http4s.dsl.Http4sDsl

/**
 * Routes for querying the weather.
 */
object WeatherRoutes {
  object latParam extends QueryParamDecoderMatcher[Double]("lat")
  object lonParam extends QueryParamDecoderMatcher[Double]("lon")
  implicit def encoder[F[_]]: EntityEncoder[F, WeatherServiceResponse] = jsonEncoderOf[F, WeatherServiceResponse]

  def routes[F[_]: Async: Functor](openWeatherService: OpenWeatherService[F]): HttpRoutes[F] = {
    object dsl extends Http4sDsl[F]; import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "weather" :? latParam(lat)  +& lonParam(lon)  => Ok(openWeatherService.query(lat, lon))
    }
  }
}
