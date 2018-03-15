#build app
FROM maven:3.5-jdk-8-alpine as builder
MAINTAINER Joodar
ENV APP=/app
RUN mkdir -p $APP
WORKDIR $APP
COPY pom.xml $APP
RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]
COPY . $APP
RUN mvn clean package

#run app with jre
FROM openjdk:8-jre-alpine
ENV APP=/app
WORKDIR $APP
COPY --from=builder /app/target/master-0.0.1-SNAPSHOT.jar $APP/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
