FROM openjdk:21

ARG JAR_FILE=target/*.jar
COPY ./target/finalShop-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://172.31.48.1:5432/products
ENV SPRING_DATASOURCE_USERNAME=dytni
ENV SPRING_DATASOURCE_PASSWORD=1331

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

# docker run -p 8080:8080 finalshopapi
# docker build -t finalshopapi .