# Estágio de build (Maven)
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
RUN cp /app/target/*.war /app/target/app.war

# Estágio final (Tomcat 9 - formato correto para ghcr.io)
FROM ghcr.io/tomcat/tomcat:9.0-jdk17-openjdk  
COPY --from=builder /app/target/app.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
