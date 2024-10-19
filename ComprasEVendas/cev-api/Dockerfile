FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

COPY src/main/resources /app/resources

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "-Dspring.config.location=/app/resources/", "/app.jar"]