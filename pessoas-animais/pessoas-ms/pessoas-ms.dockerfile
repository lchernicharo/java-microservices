FROM openjdk:11-jre
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD target/pessoas-ms-0.0.1-SNAPSHOT.jar pessoasms.jar
ADD wait-for-it.sh /usr/wait-for-it.sh
RUN chmod +x /usr/wait-for-it.sh
EXPOSE 8080
