![Build status](https://github.com/KaperD/LOB-HSE-SE-Project-Fall-2021/actions/workflows/check.yml/badge.svg)


# Sport+ Backend
This project is a backend for Sport+ service ([learn more](https://docs.google.com/presentation/d/1cWrB_O68aSAuZ332TBQ1fq2j87nwqzjYmkLEKyrs6Uc/edit?usp=sharing) about Sport+)

It provides REST API for making requests

## Running localy

Project uses Gradle build system

```bash
./gradlew bootRun
```

It will start Tomcat server on port 8080. You can specify your own port in src/main/resources/application.properties:
```properties
server.port = 7777
```
