# Use maven image to build the project
FROM maven:latest AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use eclipse-temurin image to extract the jar file
FROM eclipse-temurin:21-jre-alpine AS builder
WORKDIR extracted
COPY --from=build /app/target/*.jar app.jar
RUN java -Djarmode=tools -jar app.jar extract --layers

# Use eclipse-temurin image to run the application
FROM eclipse-temurin:21-jre-alpine
WORKDIR application
COPY --from=builder /extracted/app/dependencies/ ./
COPY --from=builder /extracted/app/spring-boot-loader/ ./
COPY --from=builder /extracted/app/snapshot-dependencies/ ./
COPY --from=builder /extracted/app/application/ ./
EXPOSE 8300
ENTRYPOINT ["java", "-jar", "app.jar"]