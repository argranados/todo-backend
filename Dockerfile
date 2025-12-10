# Usar una imagen base oficial de Java
FROM amazoncorretto:17-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el JAR generado por tu aplicación al contenedor
COPY target/todo-backend-0.0.1-SNAPSHOT.jar todo-app.jar

# Exponer el puerto en el que corre Spring Boot (por defecto 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "todo-app.jar"]