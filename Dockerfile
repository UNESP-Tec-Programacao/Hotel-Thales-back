# Estágio de build (Maven)
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copia o pom.xml e o código-fonte (ajuste o caminho se necessário)
COPY pom.xml .          # Se pom.xml estiver na raiz do projeto
COPY src ./src          # Se src/ estiver na raiz

# Build do projeto (ignora testes para acelerar o deploy)
RUN mvn clean package -DskipTests

# Padroniza o nome do .war para "app.war" (evita problemas no Tomcat)
RUN cp /app/target/*.war /app/target/app.war

# --- Estágio final (Tomcat 9) ---
FROM tomcat:9.0-jdk17-openjdk-slim

# Copia o .war para o Tomcat (ROOT.war = roda na raiz "/")
COPY --from=builder /app/target/app.war /usr/local/tomcat/webapps/ROOT.war

# Porta exposta (Tomcat usa 8080 por padrão)
EXPOSE 8080

# Comando de start (obrigatório para o Railway)
CMD ["catalina.sh", "run"]
