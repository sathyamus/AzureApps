FROM openjdk:8u191-alpine
MAINTAINER Sathya <sathyamus.az@outlook.com>

ENV APP_HOME=app
ENV APP_NAME=emailAlerts
ARG VERSION=0.0.1-SNAPSHOT

WORKDIR ${APP_HOME}

COPY pom.xml ./
COPY start.sh ./

COPY target/${APP_NAME}"-"${VERSION}".jar" ./${APP_NAME}".jar"
RUN chmod -R 744 *

CMD ["./start.sh"]
