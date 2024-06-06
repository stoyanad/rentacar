FROM adoptopenjdk:21-jre-hotspot
WORKDIR /
ADD target/rentacar-1.0-SNAPSHOT.jar //
EXPOSE 8080
ENTRYPOINT
[ "java",
"-jar",
"-Dspring.profiles.active=mysql",
"/rentacar-1.0-SNAPSHOT.jar"]
