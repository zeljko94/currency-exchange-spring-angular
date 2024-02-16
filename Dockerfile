FROM openjdk:11-jdk-slim

WORKDIR /app

COPY target/a1-currency-exchange-app-0.1.0-SNAPSHOT.jar /app/

EXPOSE 8080

CMD ["java", "-jar", "a1-currency-exchange-app-0.1.0-SNAPSHOT.jar"]
