# Build Stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime Stage
FROM amazoncorretto:17
ARG PROFILE=prod
ARG APP_VERSION=latest

WORKDIR /app
COPY --from=build /build/target/test-${APP_VERSION}-SNAPSHOT.jar /app/

EXPOSE 6969

ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}

ENTRYPOINT ["java", "-jar"]
CMD ["-Dspring.profiles.active=${ACTIVE_PROFILE}", "test-${JAR_VERSION}-SNAPSHOT.jar"]
