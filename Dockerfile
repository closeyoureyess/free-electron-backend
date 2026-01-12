FROM bellsoft/liberica-openjdk-debian:25 AS builder
WORKDIR /application

RUN apt-get update && apt-get install -y --no-install-recommends ca-certificates curl \
  && rm -rf /var/lib/apt/lists/*

COPY . .

RUN --mount=type=cache,target=/root/.m2 chmod +x mvnw \
 && ./mvnw -B -pl handle -am clean package -DskipTests

FROM bellsoft/liberica-openjre-debian:25 AS layers
WORKDIR /application
COPY --from=builder /application/handle/target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM bellsoft/liberica-openjre-debian:25
VOLUME /tmp
RUN useradd --system --create-home --shell /usr/sbin/nologin spring-user
USER spring-user

COPY --from=layers /application/dependencies/ ./
COPY --from=layers /application/spring-boot-loader/ ./
COPY --from=layers /application/snapshot-dependencies/ ./
COPY --from=layers /application/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
