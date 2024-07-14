FROM openjdk:22
LABEL maintainer="javaguides.net"
ADD target/DeliX-0.0.1-SNAPSHOT.jar springboot-docker-demo.jar
ENTRYPOINT ["java", "-jar", "springboot-docker-demo.jar"]