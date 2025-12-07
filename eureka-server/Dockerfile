# --- Etapa 1: "Builder" ---
# (Esta etapa prepara las capas usando layertools)
FROM eclipse-temurin:21-jre-alpine AS builder
WORKDIR /extracted
LABEL authors="Andy Calle"

# ¡OJO! Esto asume que solo hay UN .jar en target/.
# Es más seguro poner el nombre exacto, ej: target/eureka-server-0.0.2-SNAPSHOT.jar
ARG JAR_FILE=target/*.jar 
COPY ${JAR_FILE} app.jar

# Extrae las capas para optimizar la imagen final
RUN java -Djarmode=layertools -jar app.jar extract

# --- Etapa 2: "Final" ---
# (Esta es la imagen final que correrá)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /application

# --- LÍNEA ELIMINADA ---
# Ya no necesitamos 'curl'
# RUN apk add --no-cache curl
# ------------------------

# Crear usuario seguro
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copiar las capas optimizadas desde la etapa anterior
COPY --from=builder extracted/dependencies/ ./
COPY --from=builder extracted/spring-boot-loader/ ./
COPY --from=builder extracted/snapshot-dependencies/ ./
COPY --from=builder extracted/application/ ./

# Expone el puerto INTERNO de Eureka
EXPOSE 8761

# Comando para ejecutar la aplicación usando las capas extraídas
ENTRYPOINT ["java", "-cp","/application","org.springframework.boot.loader.launch.JarLauncher"]

