package brad.kaiser.weather.config

/**
 * Describe config files
 */
case class Config(openWeather: OpenWeatherConfig)
case class OpenWeatherConfig (url: String, apiKey: String)
