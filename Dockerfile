# Stage 1: Build the application
FROM gradle:7.6.0-jdk17-alpine AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and dependencies configuration
COPY gradle /app/gradle
COPY gradlew /app/gradlew
COPY build.gradle /app/build.gradle
COPY settings.gradle /app/settings.gradle

# Download dependencies
RUN ./gradlew build -x test --no-daemon

# Copy the source code
COPY src /app/src

# Build the application
RUN ./gradlew build -x test --no-daemon

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
