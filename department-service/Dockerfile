FROM eclipse-temurin:17

LABEL mentainer="piotrjavatestowy@gmail.com"

WORKDIR /app

COPY target/department-service-0.0.1-SNAPSHOT.jar /app/department-service-docker-demo.jar

ENTRYPOINT ["java", "-jar", "department-service-docker-demo.jar"]