FROM maven:3.6.3-jdk-11-slim AS builder
WORKDIR /appBack
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn -e -B package -Dmaven.test.skip=true

FROM openjdk:11-jdk-slim
COPY --from=builder /appBack/target/demo-0.1.jar demo-0.1.jar
EXPOSE 8080
CMD ["java", "-jar", "/demo-0.1.jar"]
