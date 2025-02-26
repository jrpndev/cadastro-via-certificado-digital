FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean install

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /app/target/cadastro-empresa-1.0-SNAPSHOT.jar /app/cadastro-empresa.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/cadastro-empresa.jar"]
