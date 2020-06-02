FROM openjdk:8-jdk-alpine
COPY ./target/carmicroservice-0.0.1.jar ./
CMD ["java","-jar","carmicroservice-0.0.1.jar"]
