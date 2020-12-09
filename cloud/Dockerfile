FROM adoptopenjdk/openjdk14
EXPOSE 8080

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} cloud.jar
ENTRYPOINT ["java","-jar","/cloud.jar"]