# Estágio de build com Maven
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app
# Copia apenas os arquivos necessários primeiro para aproveitar cache do Docker
COPY Thales-Hotel/pom.xml .
COPY Thales-Hotel/src ./src

# Build do projeto
RUN mvn clean package

# Verifica e copia o arquivo WAR gerado
RUN find /app/target -name '*.war' -exec cp {} /app/target/app.war \;

# Estágio final
FROM eclipse-temurin:21-jre-jammy

# Instalação do Tomcat
RUN apt-get update && \
    apt-get install -y wget && \
    wget https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.62/bin/apache-tomcat-9.0.62.tar.gz && \
    tar -xvzf apache-tomcat-9.0.62.tar.gz && \
    mv apache-tomcat-9.0.62 /opt/tomcat && \
    rm apache-tomcat-9.0.62.tar.gz

# Configuração do Tomcat Manager (usuário/senha)
RUN echo '<?xml version="1.0" encoding="UTF-8"?> \
<tomcat-users xmlns="http://tomcat.apache.org/xml" \
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" \
              xsi:schemaLocation="http://tomcat.apache.org/xml tomcat-users.xsd" \
              version="1.0"> \
  <role rolename="manager-gui"/> \
  <role rolename="admin-gui"/> \
  <user username="admin" password="senha123" roles="manager-gui,admin-gui"/> \
</tomcat-users>' > /opt/tomcat/conf/tomcat-users.xml

# Permite acesso remoto ao Manager
RUN sed -i 's|<Valve className="org.apache.catalina.valves.RemoteAddrValve" allow="127\.\d+\.\d+\.\d+|<!-- & -->|' /opt/tomcat/webapps/manager/META-INF/context.xml && \
    sed -i 's|<Valve className="org.apache.catalina.valves.RemoteAddrValve" allow="127\.\d+\.\d+\.\d+|<!-- & -->|' /opt/tomcat/webapps/host-manager/META-INF/context.xml

# Configuração de upload de arquivos grandes para Tomcat (200MB)
RUN sed -i 's|<Context>|<Context maxPostSize="209715200">|' /opt/tomcat/webapps/manager/META-INF/context.xml && \
    sed -i 's|<Context>|<Context maxPostSize="209715200">|' /opt/tomcat/webapps/host-manager/META-INF/context.xml

# Copia o WAR para o Tomcat
COPY --from=builder /app/target/app.war /opt/tomcat/webapps/ROOT.war

# Ajuste de memória para Tomcat
ENV CATALINA_OPTS="-Xms512m -Xmx1024m -Dfile.encoding=UTF-8"

# Expor a porta do Tomcat
EXPOSE 8080

# Iniciar o Tomcat
CMD ["/opt/tomcat/bin/catalina.sh", "run"]
