FROM openjdk:17-alpine

VOLUME /tmp

WORKDIR /app

COPY ./build/libs/statistics-service-0.0.1-SNAPSHOT.jar ./app.jar

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8083