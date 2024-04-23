package com.rmarcello.eventhubutils.consumer;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.azure.messaging.eventhubs.models.EventContext;

public class ProcessEvent implements Consumer<EventContext>{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessEvent.class);

    private final LastMessages lm;  

    public ProcessEvent(LastMessages lm) {
        this.lm = lm;
    }

    @Override
    public void accept(EventContext eventContext) {
        LOGGER.info("Processing event from partition {} with sequence number {} with body: {}",
           eventContext.getPartitionContext().getPartitionId(), eventContext.getEventData().getSequenceNumber(),
           eventContext.getEventData().getBodyAsString());
        lm.addMessage("MSG: " + eventContext.getEventData().getBodyAsString() );
    }
    
}
