# Usando uma imagem do Tomcat
FROM tomcat:9-jdk21-slim

# Copiar o arquivo WAR para o Tomcat
COPY target/Hotel-Thales-back.war /usr/local/tomcat/webapps/

# Expor a porta do Tomcat
EXPOSE 8080

# Iniciar o Tomcat
CMD ["catalina.sh", "run"]
