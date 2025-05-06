# Usando a imagem do JDK
FROM eclipse-temurin:21-jre-jammy

# Diretório de trabalho
WORKDIR /app

# Copiar o arquivo WAR gerado para o contêiner (ajuste o nome do arquivo WAR se necessário)
COPY target/Thales-Hotel.war /app/Thales-Hotel.war

# Expõe a porta 8080 (padrão para o Tomcat)
EXPOSE 8080

# Comando para rodar o .war com o Java
CMD ["java", "-jar", "/app/Thales-Hotel.war"]
