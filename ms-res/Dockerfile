# --- Etapa 1: "Builder" ---
# (Esta etapa solo extrae el .jar que ya construiste)
FROM eclipse-temurin:21-jre-alpine AS builder
WORKDIR /workspace

# --- ¡LÍNEA IMPORTANTE! ---
# ¡REVISA ESTE NOMBRE!
# Ejecuta "ls -l ms-res/target/" y pon el nombre correcto aquí.
ARG JAR_FILE=target/ms-res-0.0.2-SNAPSHOT.jar

# Copia solo ese .jar y lo renombra a "app.jar"
COPY ${JAR_FILE} app.jar

# --- Etapa 2: "Final" ---
# (Esta es la imagen final que correrá)
FROM eclipse-temurin:21-jre-alpine

# Instalar 'curl' para el healthcheck de tu docker-compose.yml
RUN apk add --no-cache curl

WORKDIR /app

# Copia el "app.jar" desde la etapa anterior
COPY --from=builder /workspace/app.jar .

# Crear usuario seguro
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Expone el puerto interno (8080 para ms-res)
EXPOSE 8080

# Comando para correr la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]





