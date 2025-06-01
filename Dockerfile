FROM eclipse-temurin:21-jdk

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

# docker run -p 8080:8080 finalshopapi
# docker build -t finalshopapi .