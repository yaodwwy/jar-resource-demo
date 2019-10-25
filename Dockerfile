FROM openjdk:8-alpine
MAINTAINER gomro <develop-groups@gomro.com>

ARG JAR_FILE
ARG APP_VERSION

ENV APP_VERSION $APP_VERSION

COPY target/jar-resource-demo-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=GMT+08","-jar","app.jar"]

EXPOSE 80
