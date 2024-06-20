FROM openjdk:17-alpine

VOLUME /tmp

WORKDIR /app

COPY ./src/main/resources/application.yaml ./app/application.yaml
COPY ./build/libs/game-service-0.0.1-SNAPSHOT.jar ./app.jar

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8084
