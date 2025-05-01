# Use uma imagem oficial do OpenJDK
FROM openjdk:21-jdk-slim

# Defina o diretório de trabalho no container
WORKDIR /app

# Copie o arquivo JAR gerado para o container
COPY target/Thales-Hotel-1.0-SNAPSHOT.jar /app

# Exponha a porta que sua aplicação Java vai usar
EXPOSE 8080

# Comando para executar o arquivo JAR
CMD ["java", "-jar", "Thales-Hotel-1.0-SNAPSHOT.jar"]
