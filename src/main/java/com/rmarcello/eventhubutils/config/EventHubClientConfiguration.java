package com.rmarcello.eventhubutils.config;

import com.azure.core.credential.AzureNamedKeyCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.azure.messaging.eventhubs.EventProcessorClient;
import com.azure.messaging.eventhubs.EventProcessorClientBuilder;
import com.azure.messaging.eventhubs.checkpointstore.blob.BlobCheckpointStore;
import com.azure.messaging.eventhubs.models.EventHubConnectionStringProperties;
import com.azure.storage.blob.BlobContainerAsyncClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.rmarcello.eventhubutils.consumer.LastMessages;
import com.rmarcello.eventhubutils.consumer.ProcessError;
import com.rmarcello.eventhubutils.consumer.ProcessEvent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventHubClientConfiguration {

    @Value("${eventhub.connection.string}")
    private String eventhubConnectionString;

    @Value("${eventhub.consumer.group}")
    private String consumerGroup;

    @Value("${storage.account.endpoint}")
    private String storageAccountEndpoint;

    @Value("${storage.account.name}")
    private String storageAccountNamme;

    @Value("${lastmessage.max}")
    private int lastMessageSize;

    @Bean
    EventHubClientBuilder eventHubClientBuilder() {
        EventHubConnectionStringProperties properties = EventHubConnectionStringProperties.parse(eventhubConnectionString);
        AzureNamedKeyCredential credential = new AzureNamedKeyCredential(properties.getSharedAccessKeyName(), properties.getSharedAccessKey());
        return new EventHubClientBuilder().credential(properties.getFullyQualifiedNamespace(), properties.getEntityPath(), credential);
    }

    @Bean
    EventHubProducerClient eventHubProducerClient(EventHubClientBuilder eventHubClientBuilder) {
        return eventHubClientBuilder.buildProducerClient();
    }

    @Bean
    @Conditional(ListenerCondition.class)
    BlobContainerClientBuilder blobContainerClientBuilder() {
        return new BlobContainerClientBuilder().credential(new DefaultAzureCredentialBuilder()
                .build())
                .endpoint(storageAccountEndpoint)
                .containerName(storageAccountNamme);
    }

    @Bean
    @Conditional(ListenerCondition.class)
    BlobContainerAsyncClient blobContainerAsyncClient(BlobContainerClientBuilder blobContainerClientBuilder) {
        return blobContainerClientBuilder.buildAsyncClient();
    }

    @Bean
    @Conditional(ListenerCondition.class)
    EventProcessorClientBuilder eventProcessorClientBuilder(BlobContainerAsyncClient blobContainerAsyncClient, ProcessEvent processEvent, ProcessError processError) {
        EventHubConnectionStringProperties properties = EventHubConnectionStringProperties.parse(eventhubConnectionString);
        AzureNamedKeyCredential credential = new AzureNamedKeyCredential(properties.getSharedAccessKeyName(), properties.getSharedAccessKey());
        return new EventProcessorClientBuilder()
                .credential(properties.getFullyQualifiedNamespace(), properties.getEntityPath(), credential)
                .consumerGroup(consumerGroup)
                .checkpointStore(new BlobCheckpointStore(blobContainerAsyncClient))
                .processEvent(processEvent)
                .processError(processError);
    }

    @Bean
    @Conditional(ListenerCondition.class)
    EventProcessorClient eventProcessorClient(EventProcessorClientBuilder eventProcessorClientBuilder) {
        return eventProcessorClientBuilder.buildEventProcessorClient();
    }

    @Bean
    LastMessages getLastMessages() {
        return new LastMessages(lastMessageSize);
    }

    @Bean
    ProcessEvent getProcessEvent(LastMessages lm) {
        return new ProcessEvent(lm);
    }

    @Bean
    ProcessError getProcessError(LastMessages lm) {
        return new ProcessError(lm);
    }

}