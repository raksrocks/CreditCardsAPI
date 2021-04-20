FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY target/creditcards-api-0.0.1-SNAPSHOT.jar creditcards-api.jar
ENTRYPOINT ["java","-jar","/creditcards-api.jar"]