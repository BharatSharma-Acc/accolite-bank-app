# Maven build container 

FROM maven:3.6.3-jdk-8 AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

#pull base image

FROM eclipse-temurin:11

#expose port 8080
EXPOSE 8080

#default command
ENTRYPOINT ["java","-jar","/data/app.jar"]

#copy Bank App to docker image from builder image

COPY --from=maven_build /tmp/target/*.jar /data/app.jar