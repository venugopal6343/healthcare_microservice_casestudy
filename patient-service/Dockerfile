# layer 1
# Use maven image to build the project
FROM maven:latest AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# layer 2
# Use eclipse-temurin image to extract the jar file
FROM eclipse-temurin:21-jre-alpine as builder
COPY --from=build /app/target/*.jar app.jar
WORKDIR extracted
ADD target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

# layer 3
# Use eclipse-temurin image to run the application
FROM eclipse-temurin:21-jre-alpine
WORKDIR application
COPY --from=builder extracted/dependencies/ ./
COPY --from=builder extracted/spring-boot-loader/ ./
COPY --from=builder extracted/snapshot-dependencies/ ./
COPY --from=builder extracted/application/ ./
EXPOSE 8100
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]