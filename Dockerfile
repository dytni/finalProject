FROM openjdk:21
WORKDIR /app
COPY target/finalShop-0.0.1-SNAPSHOT.jar /app/shop.jar

ENTRYPOINT ["java", "-jar", "shop.jar"]