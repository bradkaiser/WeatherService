package brad.kaiser.weather.services

import brad.kaiser.weather.config.OpenWeatherConfig
import brad.kaiser.weather.models.{OpenWeatherResponse, WeatherServiceResponse}
import cats.Functor
import cats.effect.{Async, IO}
import cats.implicits.toFunctorOps
import org.http4s.blaze.client.{BlazeClient, BlazeClientBuilder}
import org.http4s.circe.CirceEntityCodec._
import org.http4s.circe.jsonOf
import io.circe._
import io.circe.generic.auto._
import io.circe.syntax.EncoderOps
import org.http4s.client.Client
import org.http4s.{EntityDecoder, Uri}

case class OpenWeatherService[F[_]](config: OpenWeatherConfig, client: Client[F]) {
  val onecall: Uri = Uri.unsafeFromString(config.url) / "data" / "2.5" / "onecall"
//  implicit val decoder: EntityDecoder[IO, OpenWeatherResponse] = jsonOf[IO, OpenWeatherResponse]

  def query(lat: Double, lon: Double)(implicit fa: Async[F], fu: Functor[F]): F[WeatherServiceResponse] = {
    // TODO bk, don't use client every time
    val uri = onecall  +? ("lat", lat) +? ("lon", lon) +? ("appid", config.apiKey) +? ("exclude", "minutely,hourly,daily")
    client.expect[OpenWeatherResponse](uri).map(WeatherServiceResponse.apply)
//    BlazeClientBuilder[F].resource.use { client =>
//      val uri = onecall  +? ("lat", lat) +? ("lon", lon) +? ("appid", config.apiKey) +? ("exclude", "minutely,hourly,daily")
//      client.expect[OpenWeatherResponse](uri).map(WeatherServiceResponse.apply)
//    }
  }
}
