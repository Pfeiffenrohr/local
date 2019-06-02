FROM adoptopenjdk/openjdk11:latest
WORKDIR /
ADD target/Dockertest-0.0.1-SNAPSHOT.jar HelloWorld.jar
EXPOSE 8080
CMD java - jar HelloWorld.jar  

FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
COPY target/Dockertest-0.0.1-SNAPSHOT.jar /opt/app/japp.jar
CMD ["java", "-jar", "/opt/app/japp.jar"]