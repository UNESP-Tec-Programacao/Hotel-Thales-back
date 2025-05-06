# Estágio de build com Maven
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copia apenas os arquivos necessários
COPY Thales-Hotel/pom.xml .
COPY Thales-Hotel/src ./src

# Build do projeto
RUN mvn clean package

# Verifica e renomeia o arquivo WAR gerado
RUN find /app/target -name '*.war' -exec cp {} /app/target/app.war \;
