FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD /target/techService-task.jar techService-task.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/techService-task.jar"]