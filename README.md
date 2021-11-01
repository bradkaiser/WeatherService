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
Next steps I might take in this project include:
1. Add more logging.
2. Possibly set up more descriptive http response codes.
3. Integrate it with a CI system like Travis or Jenkins
4. Flesh out the unit tests
5. Add some integration tests
6. Define a docker image with a dockerfile or jib.
7. Add a helm chart.