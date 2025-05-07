# Estágio de build com Maven
FROM maven:3.9-eclipse-temurin-21 AS builder

# Define o diretório de trabalho
WORKDIR /app

# Copia o projeto inteiro para dentro da imagem
COPY Thales-Hotel /app

# Executa o build do projeto (gera o WAR)
RUN mvn clean package

# Copia o .war gerado e renomeia para app.war
RUN find /app/target -name 'Thales-Hotel.war' -exec cp {} /app/target/app.war \;
