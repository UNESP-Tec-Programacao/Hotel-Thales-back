# Etapa de Build com Maven
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copiar o pom.xml e src primeiro (aproveitar cache)
COPY pom.xml ./ 
COPY src ./src

# Rodar o build Maven para gerar o .war
RUN mvn clean package

# Copiar o .war gerado
RUN find /app/target -name '*.war' -exec cp {} /app/Thales-Hotel.war \;

# Estágio Final com JDK para rodar o WAR
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copiar o WAR gerado para o contêiner
COPY --from=builder /app/Thales-Hotel.war /app/Thales-Hotel.war

# Expõe a porta 8080
EXPOSE 8080

# Comando para rodar o WAR
CMD ["java", "-jar", "/app/Thales-Hotel.war"]
