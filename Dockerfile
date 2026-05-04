# ── Stage 1: build ────────────────────────────────────────────────────────────
FROM gradle:8.7-jdk21 AS builder

WORKDIR /app

# Copy gradle files first for layer caching
COPY build.gradle settings.gradle ./
COPY gradle gradle

# Download dependencies
RUN gradle dependencies --no-daemon || true

# Copy source and build
COPY src ./src
RUN gradle bootJar --no-daemon -x test


FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]