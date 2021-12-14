FROM gradle:7.2-jdk11 AS build

WORKDIR /app
COPY src ./src
COPY config ./config
COPY build.gradle.kts settings.gradle.kts ./

RUN gradle clean build

FROM openjdk:11-jre-slim
EXPOSE 8080

COPY --from=build /app/build/libs/SportPlus*.jar /SportPlus.jar

CMD ["java", "-jar", "/SportPlus.jar"]
