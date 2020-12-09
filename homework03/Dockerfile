FROM adoptopenjdk/openjdk14
EXPOSE 8080

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} hw03.jar
ENTRYPOINT ["java","-jar","/hw03.jar"]