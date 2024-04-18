package com.rmarcello.eventhubutils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import com.azure.messaging.eventhubs.EventProcessorClient;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    //@Autowired
    //EventProcessorClient eventProcessorClient;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        // Your custom logic here
        System.out.println("I have just started up");
        //eventProcessorClient.start();
    }

}