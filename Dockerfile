FROM openjdk:11.0.3-jdk
VOLUME /tmp
ADD target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT exec java -Djava.awt.headless=true -jar /app.jar