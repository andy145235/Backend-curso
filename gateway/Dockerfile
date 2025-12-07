FROM eclipse-temurin:21-jre-alpine AS builder
WORKDIR /workspace

# ¡VERIFICA ESTE NOMBRE! (ls -l gateway/target/)
ARG JAR_FILE=target/gateway-0.0.1-SNAPSHOT.jar 

COPY ${JAR_FILE} app.jar

# --- Etapa 2: "Final" ---
# (SIN --platform=linux/amd64)
FROM eclipse-temurin:21-jre-alpine

# Instalar 'curl' para el healthcheck (si el gateway lo necesita)
RUN apk add --no-cache curl

WORKDIR /app
COPY --from=builder /workspace/app.jar .

# Crear usuario seguro
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Expone el puerto INTERNO (8040)
EXPOSE 8090

# El ENTRYPOINT simple
ENTRYPOINT ["java", "-jar", "app.jar"]