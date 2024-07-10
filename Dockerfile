FROM openjdk:21
WORKDIR /app
COPY target/finalShop-0.0.1-SNAPSHOT.jar /app/shop.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/shop.jar"]