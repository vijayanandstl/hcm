# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim
VOLUME /tmp
ARG JAR_FILE=target/hcm-core-*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080 