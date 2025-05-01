# Estágio de build com Maven
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app
# Copia apenas os arquivos necessários primeiro para aproveitar cache do Docker
COPY Thales-Hotel/pom.xml .
COPY Thales-Hotel/src ./src

# Build do projeto
RUN mvn clean package

# Verifica e copia o arquivo WAR gerado
RUN find /app/target -name '*.war' -exec cp {} /app/target/app.war \;

# Estágio final
FROM eclipse-temurin:21-jre-jammy

# Instalação do Tomcat
RUN apt-get update && \
    apt-get install -y wget && \
    wget https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.62/bin/apache-tomcat-9.0.62.tar.gz && \
    tar -xvzf apache-tomcat-9.0.62.tar.gz && \
    mv apache-tomcat-9.0.62 /opt/tomcat && \
    rm apache-tomcat-9.0.62.tar.gz

# Copia o WAR para o Tomcat
COPY --from=builder /app/target/app.war /opt/tomcat/webapps/ROOT.war

# Configuração final
EXPOSE 8080
CMD ["/opt/tomcat/bin/catalina.sh", "run"]
