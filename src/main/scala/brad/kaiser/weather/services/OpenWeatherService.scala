package brad.kaiser.weather.services

import brad.kaiser.weather.config.OpenWeatherConfig
import brad.kaiser.weather.models.{OpenWeatherResponse, WeatherServiceResponse}
import cats.Functor
import cats.effect.Async
import cats.implicits.toFunctorOps
import org.http4s.circe.CirceEntityCodec._
import io.circe.generic.auto._
import org.http4s.client.Client
import org.http4s.Uri

/**
 * This service is responsible for communicating with Open Weather Service
 * @param config the config for this client
 * @param client an http client for accessing open weather service
 * @tparam F
 */
case class OpenWeatherService[F[_]: Async: Functor](config: OpenWeatherConfig, client: Client[F]) {
  val onecall: Uri = Uri.unsafeFromString(config.url) / "data" / "2.5" / "onecall"

  def query(lat: Double, lon: Double): F[WeatherServiceResponse] = {
    val uri = onecall +? ("lat", lat) +? ("lon", lon) +? ("appid", config.apiKey) +? ("exclude", "minutely,hourly,daily")
    client.expect[OpenWeatherResponse](uri).map(WeatherServiceResponse.apply)
  }
}
