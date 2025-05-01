# Usando uma imagem do OpenJDK 21
FROM openjdk:21-jdk-slim

# Baixar e configurar o Tomcat
RUN apt-get update && apt-get install -y wget && \
    wget https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.62/bin/apache-tomcat-9.0.62.tar.gz && \
    tar -xvzf apache-tomcat-9.0.62.tar.gz && \
    mv apache-tomcat-9.0.62 /opt/tomcat

# Copiar o WAR para o Tomcat
COPY target/Hotel-Thales-back.war /opt/tomcat/webapps/

# Expor a porta do Tomcat
EXPOSE 8080

# Iniciar o Tomcat
CMD ["/opt/tomcat/bin/catalina.sh", "run"]
