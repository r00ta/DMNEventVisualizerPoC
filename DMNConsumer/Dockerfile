#FROM maven:3.6.2-jdk-8 
FROM openjdk:8
COPY trusty-service/ /trusty-service/
WORKDIR trusty-service/
CMD ["./mvnw", "compile", "quarkus:dev"]
#CMD ["sleep", "10000"] 
