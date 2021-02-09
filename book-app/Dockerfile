FROM adoptopenjdk:11-jre-hotspot-bionic
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} book-app.jar
ENTRYPOINT ["java", "-jar", "book-app"]