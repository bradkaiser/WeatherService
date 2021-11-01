package brad.kaiser.weather.models

import org.scalatest.flatspec.AnyFlatSpec
import io.circe.generic.auto._
import io.circe.parser._
import org.scalatest.EitherValues
import org.scalatest.matchers.should.Matchers

class OpenWeatherResponseSpec extends AnyFlatSpec with Matchers with EitherValues {

  "OpenWeatherResponse" should "be parseable from open weather json" in {
    val json = """{
                 |  "lat": 33.74,
                 |  "lon": 84.388,
                 |  "timezone": "Asia/Shanghai",
                 |  "timezone_offset": 28800,
                 |  "current": {
                 |    "dt": 1635568550,
                 |    "sunrise": 1635554372,
                 |    "sunset": 1635593551,
                 |    "temp": 263.68,
                 |    "feels_like": 260.08,
                 |    "pressure": 1026,
                 |    "humidity": 31,
                 |    "dew_point": 251.07,
                 |    "uvi": 6.69,
                 |    "clouds": 0,
                 |    "visibility": 10000,
                 |    "wind_speed": 1.76,
                 |    "wind_deg": 297,
                 |    "wind_gust": 2.57,
                 |    "weather": [
                 |      {
                 |        "id": 800,
                 |        "main": "Clear",
                 |        "description": "clear sky",
                 |        "icon": "01d"
                 |      }
                 |    ]
                 |  }
                 |}""".stripMargin

    val result = decode[OpenWeatherResponse](json).value
    result.current.temp shouldBe 263.68
    result.current.weather.size shouldBe 1
    result.current.weather.head.main shouldBe "Clear"
    result.current.weather.head.description shouldBe "clear sky"
    result.alerts shouldBe None
  }
}
