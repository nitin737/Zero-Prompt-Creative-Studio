# Stage 1: Build
FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /app

# Copy gradle files from backend folder
COPY backend/gradlew .
COPY backend/gradlew.bat .
COPY backend/gradle gradle
COPY backend/build.gradle backend/settings.gradle ./

# Ensure gradlew has execution permissions
RUN chmod +x gradlew

# Pre-fetch dependencies to speed up builds
RUN ./gradlew dependencies --no-daemon

# Copy source and build
COPY backend/src src
RUN ./gradlew bootJar --no-daemon

# Stage 2: Run
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

# Create directory for images
RUN mkdir -p /app/generated-images

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
