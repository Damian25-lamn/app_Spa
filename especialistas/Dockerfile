# Usamos una imagen base de OpenJDK 21
FROM eclipse-temurin:21-jdk-jammy

# Directorio dentro del contenedor donde estará la app
WORKDIR /app

# Copiamos el JAR generado en target al contenedor
COPY target/especialistas-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto donde corre la app (mismo que en application.properties)
EXPOSE 8084

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
