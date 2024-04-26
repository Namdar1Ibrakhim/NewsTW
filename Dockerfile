docker-compose -f compose.yml up# Dockerfile

# Stage 1: Build
FROM openjdk:21-jdk AS build

# Установка Maven
RUN apt-get update && \
    apt-get install -y maven

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package

# Stage 2: Run
FROM openjdk:21-jdk

WORKDIR /app
COPY --from=build /app/target/app.zhardem-1.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]