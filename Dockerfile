FROM adoptopenjdk/openjdk11:latest
MAINTAINER rushikesh
COPY petstoreservice-0.0.1-SNAPSHOT.jar petstoreservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/petstoreservice-0.0.1-SNAPSHOT.jar"]
