#FROM openjdk:8-jdk-alpine
#COPY visseModas.jar visseModas.jar
#ENTRYPOINT ["java","-jar","/visseModas.jar"]


FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8081
ADD  visseModas.jar visseModas.jar
ENTRYPOINT ["java","-jar","visseModas.jar"]