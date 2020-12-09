FROM x86_64-alpine-jdk-14.0.2_12-slim
EXPOSE 8080

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} cloud.jar
ENTRYPOINT ["java","-jar","/cloud.jar"]