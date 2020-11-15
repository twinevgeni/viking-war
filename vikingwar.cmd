cd %~dp0
gradle build
java -jar .\build\libs\viking.war.jar %*
