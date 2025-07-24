# Usamos una imagen base de OpenJDK 21
FROM eclipse-temurin:21-jdk-jammy

# Directorio dentro del contenedor donde estará la app
WORKDIR /app

# Copiamos el JAR generado en target al contenedor
COPY target/billing-1.0.0-SNAPSHOT.jar app.jar

# Exponemos el puerto donde corre la app (mismo que en application.properties)
EXPOSE 8083

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
