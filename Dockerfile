#run app with jre
FROM openjdk:8-jre-alpine
ENV APP=/app
WORKDIR $APP
COPY /target/master-0.0.1-SNAPSHOT.jar $APP/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
