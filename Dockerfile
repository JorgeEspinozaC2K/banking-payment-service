FROM openjdk:8-jdk-slim
COPY "./target/banking-payment-service-0.1.jar" "payment-service.jar"
ENTRYPOINT ["java","-jar","payment-service.jar"]