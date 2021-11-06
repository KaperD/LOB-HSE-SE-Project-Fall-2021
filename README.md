![Build status](https://github.com/KaperD/LOB-HSE-SE-Project-Fall-2021/actions/workflows/check.yml/badge.svg)


# Sport+ Backend
This project is a backend for Sport+ service ([learn more](https://docs.google.com/presentation/d/1cWrB_O68aSAuZ332TBQ1fq2j87nwqzjYmkLEKyrs6Uc/edit?usp=sharing) about Sport+)

It provides REST API for making requests

NB: project is in Incubating status, it means that public API can be changed

## Roadmap
You can find it in [GitHub Projects](https://github.com/KaperD/LOB-HSE-SE-Project-Fall-2021/projects/2)

## Running localy
Project uses Gradle build system

```bash
./gradlew bootRun
```

It will start Tomcat server on port 8080. You can specify your own port in src/main/resources/application.properties:
```properties
server.port = 7777
```

## Authors
Alexey Luchinin ([GitHub profile](https://github.com/alex999990009))

Ilya Onoffriychuk ([GitHub profile](https://github.com/ilyaonoff))

Danil Bubnov ([GitHub profile](https://github.com/KaperD))

## License
Distributed under the [MIT license](https://choosealicense.com/licenses/mit/)
