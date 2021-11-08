![Build status](https://github.com/KaperD/LOB-HSE-SE-Project-Fall-2021/actions/workflows/check.yml/badge.svg)


# Sport+ Backend
This project is a backend for Sport+ service ([learn more](https://docs.google.com/presentation/d/1cWrB_O68aSAuZ332TBQ1fq2j87nwqzjYmkLEKyrs6Uc/edit?usp=sharing) about Sport+)

It provides REST API for making requests

NB: project is in Incubating status, it means that public API can be changed

## Roadmap
You can find it in [GitHub Projects](https://github.com/KaperD/LOB-HSE-SE-Project-Fall-2021/projects/2)

## Swagger
You can find [here](https://kaperd.github.io/LOB-HSE-SE-Project-Fall-2021/)

## Running locally
To run project you need an instance of PostgreSQL. 
By default, it search it on localhost:5433 with 'postgres' as DB name, username and password.
You can change it in src/main/resources/application.properties 

Project uses Gradle build system. You can use this command to run project:

```bash
./gradlew bootRun
```

It will start Tomcat server on port 8080. You can specify your own port in src/main/resources/application.properties:
```properties
server.port = 7777
```

## Example
### Posting player:
```bash
$ cat player.json
{
  "name": "Alex",
  "country": "Canada",
  "position": "Centre forward",
  "height": 190,
  "leadingFoot": "left",
  "goals": 0,
  "saves": 0,
  "teamId": null
}
$ curl -s -X POST -H "Content-Type: application/json" -d "@player.json" localhost:8080/football/player | jq
{
  "id": 219,
  "name": "Alex",
  "country": "Canada",
  "position": "Centre forward",
  "height": 190,
  "leadingFoot": "left",
  "goals": 0,
  "saves": 0,
  "teamId": null
}
```

### Getting player:
```bash
$ curl -s localhost:8080/football/player/219 | jq
{
  "id": 219,
  "name": "Alex",
  "country": "Canada",
  "position": "Centre forward",
  "height": 190,
  "leadingFoot": "left",
  "goals": 0,
  "saves": 0,
  "teamId": null
}
```

## Authors
Alexey Luchinin ([GitHub profile](https://github.com/alex999990009))

Ilya Onoffriychuk ([GitHub profile](https://github.com/ilyaonoff))

Danil Bubnov ([GitHub profile](https://github.com/KaperD))

## License
Distributed under the [MIT license](https://choosealicense.com/licenses/mit/)
