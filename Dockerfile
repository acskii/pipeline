FROM openjdk:17-jdk

WORKDIR /app

COPY target/teddies-0.0.1-SNAPSHOT.jar /app/teddies.jar

EXPOSE 8080

CMD ["java", "-jar", "teddies.jar"]