FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/Calculator_java-1.0-SNAPSHOT.jar scientific_cal.jar
CMD ["java", "-jar", "scientific_cal.jar"]
