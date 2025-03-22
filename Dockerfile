# Stage 1: Build the application using Maven
FROM sapmachine:17-jdk-ubuntu AS builder
WORKDIR /app

# Copy all project files, then build the application
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew build -x test --no-daemon

# Stage 2: Run the application with a minimal JRE image
FROM sapmachine:17-jre-ubuntu
WORKDIR /app

# Copy the jar from the builder stage (update jar name as necessary)
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]