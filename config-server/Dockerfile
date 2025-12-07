# Usamos una imagen ligera de Java 17 (o 21 si usas esa)
FROM eclipse-temurin:21-jdk-alpine

# Creamos una carpeta para trabajar
WORKDIR /app

# Copiamos el archivo .jar generado (asegúrate de hacer mvn clean package antes)
# El asterisco * ayuda a que encuentre el nombre sin importar la versión exacta
COPY target/*.jar app.jar

# Exponemos el puerto 8888
EXPOSE 8888

# Comando para iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]