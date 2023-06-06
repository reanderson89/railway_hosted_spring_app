#
# Build stage
#
FROM maven:3.6.0-jdk-20.0.1 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:20.0.1
COPY --from=build /home/app/target/railway_hosted_spring_app-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]