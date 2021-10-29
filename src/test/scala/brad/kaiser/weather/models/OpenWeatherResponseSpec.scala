package brad.kaiser.weather.models

import org.scalatest.flatspec.AnyFlatSpec
import io.circe._
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.circe.CirceEntityDecoder._
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._



class OpenWeatherResponseSpec extends AnyFlatSpec {

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

    val result = decode[OpenWeatherResponse](json)
    println(result)


    // TODO BK finish test


  }

}
