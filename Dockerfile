FROM maven:3.8.6-openjdk-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk

WORKDIR /app

COPY --from=build /app/target/finalShop-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/products
ENV SPRING_DATASOURCE_USERNAME=dytni
ENV SPRING_DATASOURCE_PASSWORD=1331

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
