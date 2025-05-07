# Etapa 1: Build com Maven
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY Thales-Hotel/pom.xml .
COPY Thales-Hotel/src ./src

RUN mvn clean package

# Etapa 2: Runtime com JDK (para apps Spring Boot com Tomcat embutido)
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copia o artefato gerado da etapa anterior
COPY --from=builder /app/target/Thales-Hotel.war app.war

# Expõe a porta padrão do Spring Boot/Tomcat
EXPOSE 8080

# Executa a aplicação
ENTRYPOINT ["java", "--enable-preview", "-jar", "app.war"]
