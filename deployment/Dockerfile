FROM gradle:8.14.3-jdk21-alpine AS builder
WORKDIR /home/gradle/src
COPY build.gradle settings.gradle main.gradle gradle.properties ./
RUN gradle dependencies --no-daemon
COPY applications/ ./applications/
COPY domain/ ./domain/
COPY infrastructure/ ./infrastructure/
RUN gradle build -x test --no-daemon

FROM amazoncorretto:21-alpine3.22
WORKDIR /app
RUN addgroup -S appgroup && adduser -S -G appgroup appuser
COPY --from=builder /home/gradle/src/applications/app-service/build/libs/AuthenticationApi.jar AuthenticationApi.jar
VOLUME /tmp
RUN chown appuser:appgroup AuthenticationApi.jar
ENV JAVA_OPTS=" -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=5s --start-period=15s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1
USER appuser
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar AuthenticationApi.jar" ]