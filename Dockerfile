FROM maven:3.8.1-openjdk-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
COPY lombok.config /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM adoptopenjdk/openjdk11:alpine
MAINTAINER James Botelho

COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT ["java", "-Xmx512m","-jar","/app.jar"]

EXPOSE 8080