# Imagem base com Java 21
FROM eclipse-temurin:21-jdk

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo WAR gerado para o container
COPY target/Thales-Hotel.war app.war

# Exposição da porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar o app
ENTRYPOINT ["java", "-jar", "app.war"]
