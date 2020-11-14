FROM openjdk:14
MAINTAINER Mateusz Zuchnik
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]