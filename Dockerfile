FROM openjdk:17-jdk-slim
EXPOSE 8000
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
