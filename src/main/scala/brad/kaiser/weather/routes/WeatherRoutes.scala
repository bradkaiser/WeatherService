package brad.kaiser.weather.routes

import brad.kaiser.weather.models.{OpenWeatherResponse, WeatherServiceResponse}
import brad.kaiser.weather.services.OpenWeatherService
import cats.Functor
import cats.effect._
import cats.syntax.all._
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.circe.CirceEntityCodec._
import io.circe.generic.auto._
import org.http4s.circe.jsonOf
import org.http4s.circe.jsonEncoderOf
import org.http4s.dsl.Http4sDsl


object WeatherRoutes {
  object latParam extends QueryParamDecoderMatcher[Double]("lat")
  object lonParam extends QueryParamDecoderMatcher[Double]("lon")
  implicit def decoder[F[_]: Concurrent]: EntityDecoder[F, WeatherServiceResponse] = jsonOf[F, WeatherServiceResponse]
  implicit def encoder[F[_]]: EntityEncoder[F, WeatherServiceResponse] = jsonEncoderOf[F, WeatherServiceResponse]

  def routes[F[_]: Async: Functor](openWeatherService: OpenWeatherService[F]): HttpRoutes[F] = {
    object dsl extends Http4sDsl[F]; import dsl._
    HttpRoutes.of[F] {
      //case GET -> Root / "weather" :? latParam(lat)  +& lonParam(lon)  => openWeatherService.query(lat, lon).flatMap(Ok(_))
      //    case GET -> Root / "weather" :? latParam(lat)  +& lonParam(lon)  => Ok(openWeatherService.query(lat, lon))
      case GET -> Root / "weather" :? latParam(lat)  +& lonParam(lon)  => for {
        result <- openWeatherService.query(lat, lon)
        response <- Ok(result)
      } yield response
    }
  }
}
