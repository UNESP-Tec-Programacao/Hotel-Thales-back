# Etapa de build com Maven
FROM maven:3.9-eclipse-temurin-21 AS builder

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o projeto Maven da pasta "Thales-Hotel" para dentro do container
COPY Thales-Hotel/ ./Thales-Hotel/

# Entra na pasta do projeto e executa o build
WORKDIR /app/Thales-Hotel

# Roda o build (pulando os testes, opcionalmente)
RUN mvn clean package -DskipTests

# Copia o WAR gerado para fora
RUN cp target/*.war /app/app.war
RUN ls -lh /app/app.war
