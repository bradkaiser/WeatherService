package brad.kaiser.weather.models

/**
 * Represents the fields we care about from an open weather response
 * See https://openweathermap.org/api/one-call-api#parameter
 */
case class OpenWeatherResponse(current: Current, alerts: Option[Seq[Alert]])
case class Current(temp: Double, weather: Seq[WeatherCondition])
case class WeatherCondition(main: String, description: String)
case class Alert(event: String, description: String)


