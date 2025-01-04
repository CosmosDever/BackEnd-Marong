# Use a Maven image to build the Spring Boot application
FROM maven:3.9.4-eclipse-temurin-17 as build

# Set the working directory inside the build container
WORKDIR /app

# Copy the Maven project files into the container
COPY pom.xml .
COPY src ./src

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Use a lightweight JDK image to run the Spring Boot application
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory inside the runtime container
WORKDIR /app

# Copy the jar file from the build container
COPY --from=build /app/target/BackEnd-Marong-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Set environment variables for Spring Boot (optional, can also be set in docker-compose.yml)
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/marong_db
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]