# EventHubUtils
This project is a Java (SpringBoot) application that can be used as utility to work with Azure Event Hub. It can be used to send/read messages.


## Development

### Comands
mvn spring-boot:run

### useful links links
- [spring-cloud-azure-overview](https://learn.microsoft.com/en-us/azure/developer/java/spring-framework/spring-cloud-azure-overview)
- [azure-spring-boot-samples](https://github.com/Azure-Samples/azure-spring-boot-samples/blob/main/README.md)
- [eventhubconnectionstringproperties](https://learn.microsoft.com/en-us/java/api/com.azure.messaging.eventhubs.models.eventhubconnectionstringproperties?view=azure-java-stable)

### test

http://localhost:8080/swagger-ui/index.html
http://localhost:8080/v3/api-docs

Invoke:
```
curl -X 'POST' -H 'Content-Type: application/json' -d '{
    "idMessage": "64c22c1d668ac678831353113",
    "message": "Hello"
}' -v 'http://localhost:8080/send'
```



## Docker
### Build 
```bash
docker build --build-arg JAR_FILE=target/*.jar -t rmarcello/event-hub-utils:0.0.1 .
```

more info at https://spring.io/guides/topicals/spring-boot-docker/

### Run
```bash
docker run -p 8080:8080 -e EVENTHUB_CONNECTION_STRING="Endpoint=sb://<eventhubname>.servicebus.windows.net/;SharedAccessKeyName=<keyname>;SharedAccessKey=<accesskey>;EntityPath=<pathname>" rmarcello/event-hub-utils:0.0.1
```