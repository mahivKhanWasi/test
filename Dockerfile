# Build Stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml ./
RUN mvn dependency:go-offline  # Preload dependencies to optimize build
COPY src ./src
RUN mvn clean package -DskipTests  # Build the JAR file without running tests

# Runtime Stage
FROM amazoncorretto:17
ARG PROFILE=prod
ARG APP_VERSION=0.0.1

WORKDIR /app
COPY --from=build /build/target/test-${APP_VERSION}-SNAPSHOT.jar /app/

EXPOSE 6969

# Set runtime environment variables
ENV ACTIVE_PROFILE=${PROFILE}
ENV APP_VERSION=${APP_VERSION}

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${ACTIVE_PROFILE}", "/app/test-0.0.1-SNAPSHOT.jar"]
