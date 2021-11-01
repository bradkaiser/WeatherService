# WeatherService
A service for getting info about the weather.

### How to Build
Use the normal sbt commands to compile, test, and build an uber jar.

`$ sbt compile`

`$ sbt test`

`$ sbt assembly`

### How to Run
This app requires an Open Weather api key to run. The easiest way
to supply it is with the APP_ID environment variable.

`$ APP_ID=<your api key> sbt run`

You can also run from the uber jar

`$ APP_ID=<your api key> java -jar target/scala-2.13/WeatherService-assembly-0.1.jar`

### TODO
If I was going to continue developing this project I would:
1. Integrate it with a CI system like Travis or Jenkins
2. Flesh out the unit tests
3. Add some integration tests
4. Define a docker image with a dockerfile or jib.
5. Add a helm chart.