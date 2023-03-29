FROM openjdk:19-jdk-alpine

WORKDIR database
COPY initDB.mv.db .

RUN wget https://search.maven.org/remotecontent?filepath=com/h2database/h2/1.4.200/h2-1.4.200.jar

ENTRYPOINT ["java", "-cp", "h2-1.4.200.jar", "org.h2.tools.Server"]
CMD ["-tcp", "-tcpAllowOthers"]
