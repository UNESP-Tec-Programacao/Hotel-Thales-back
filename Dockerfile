FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY Thales-Hotel/pom.xml .
COPY Thales-Hotel/src ./src
RUN mvn clean package && \
    find /app/target -name '*.war' -exec cp {} /app/target/application.war \;

FROM eclipse-temurin:21-jre-jammy

RUN apt-get update && apt-get install -y wget && \
    wget https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.62/bin/apache-tomcat-9.0.62.tar.gz && \
    tar -xvzf apache-tomcat-9.0.62.tar.gz && \
    mv apache-tomcat-9.0.62 /opt/tomcat && \
    rm apache-tomcat-9.0.62.tar.gz

COPY --from=builder /app/target/application.war /opt/tomcat/webapps/Hotel-Thales-back.war

EXPOSE 8080
CMD ["/opt/tomcat/bin/catalina.sh", "run"]
