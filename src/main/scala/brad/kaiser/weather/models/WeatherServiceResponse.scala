package brad.kaiser.weather.models

/**
 * Our response based on the open weather data.
 */
case class WeatherServiceResponse(temp: String,
                                  description: WeatherCondition,
                                  alerts: Seq[Alert])

object WeatherServiceResponse {
  def apply(ow: OpenWeatherResponse): WeatherServiceResponse = WeatherServiceResponse(
    temp = parseTemp(ow.current.temp),
    description = ow.current.weather.head,
    alerts = ow.alerts.getOrElse(Seq.empty))

  def parseTemp(temp: Double): String = temp match {
    case t if t > 300 => "hot"
    case t if t < 283 => "cold"
    case _ => "moderate"
  }
}



