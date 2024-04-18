# EventHubUtils
This projecj is a Java (SpringBoot) application that can be used as utility to work with Azure Event Hub. It can be used to send/read messages.


## Development

### Comands
mvn spring-boot:run

### useful links links
https://learn.microsoft.com/en-us/azure/developer/java/spring-framework/spring-cloud-azure-overview
https://github.com/Azure-Samples/azure-spring-boot-samples/blob/main/README.md

http://localhost:8080/swagger-ui/index.html
http://localhost:8080/v3/api-docs




## Build 
docker build --build-arg JAR_FILE=target/*.jar -t rmarcello/EventHubUtils:0.0.1 .
https://spring.io/guides/topicals/spring-boot-docker/