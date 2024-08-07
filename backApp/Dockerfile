# Etapa de compilación
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests -X

# Etapa de ejecución
FROM eclipse-temurin:17-jre-alpine AS run
WORKDIR /app
COPY --from=build /app/target/*.jar /app/my-app.jar
EXPOSE 3000
ENV API_PORT=${API_PORT}
ENV API_NAME=${API_NAME}
ENV API_VERSION=${API_VERSION}
ENV MONGO_HOST=${MONGO_HOST}
ENV MONGO_DATABASE=${MONGO_DATABASE}
ENV MONGO_USER=${MONGO_USER}
ENV MONGO_PASS=${MONGO_PASS}
ENV POSTGRES_HOST=${POSTGRES_HOST}
ENV POSTGRES_HOST_PROD=${POSTGRES_HOST_PROD}
ENV POSTGRES_PORT=${POSTGRES_PORT}
ENV POSTGRES_DATABASE=${POSTGRES_DATABASE}
ENV POSTGRES_USER=${POSTGRES_USER}
ENV POSTGRES_CONT=${POSTGRES_CONT}
ENV POSTGRES_SSL=${POSTGRES_SSL}
ENV JWT_TOKEN=${JWT_TOKEN}
ENV PROFILE=${PROFILE}
ENV API_PORT=${API_PORT}
# Entrypoint con variables de entorno
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${PROFILE}", "-Dapi.port=${API_PORT}", "-Dapi.name=${API_NAME}", "-Dapi.version=${API_VERSION}", "-Dmongo.host=${MONGO_HOST}", "-Dmongo.database=${MONGO_DATABASE}", "-Dmongo.user=${MONGO_USER}", "-Dmongo.pass=${MONGO_PASS}", "-Dpostgres.host=${POSTGRES_HOST}", "-Dpostgres.host.prod=${POSTGRES_HOST_PROD}", "-Dpostgres.port=${POSTGRES_PORT}", "-Dpostgres.database=${POSTGRES_DATABASE}", "-Dpostgres.user=${POSTGRES_USER}", "-Dpostgres.cont=${POSTGRES_CONT}", "-Dpostgres.ssl=${POSTGRES_SSL}", "-Djwt.token=${JWT_TOKEN}", "/app/my-app.jar"]
