# Usar una imagen base oficial de Java
FROM openjdk:17-jdk-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el JAR generado por tu aplicación al contenedor
COPY target/todo-backend-0.0.1-SNAPSHOT.jar

# Exponer el puerto en el que corre Spring Boot (por defecto 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]