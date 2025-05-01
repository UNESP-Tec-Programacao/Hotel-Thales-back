FROM maven:3.8.6-openjdk-21-slim AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:21-jdk-slim

RUN apt-get update && apt-get install -y wget && \
    wget https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.62/bin/apache-tomcat-9.0.62.tar.gz && \
    tar -xvzf apache-tomcat-9.0.62.tar.gz && \
    mv apache-tomcat-9.0.62 /opt/tomcat && \
    rm apache-tomcat-9.0.62.tar.gz

COPY --from=builder /app/target/Hotel-Thales-back.war /opt/tomcat/webapps/

EXPOSE 8080
CMD ["/opt/tomcat/bin/catalina.sh", "run"]
