FROM adoptopenjdk/openjdk11:alpine

MAINTAINER James Botelho

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Xmx512m","-jar","/app.jar"]

EXPOSE 8080