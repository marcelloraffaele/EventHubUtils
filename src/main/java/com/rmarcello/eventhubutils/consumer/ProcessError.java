package com.rmarcello.eventhubutils.consumer;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.azure.messaging.eventhubs.models.ErrorContext;

public class ProcessError implements Consumer<ErrorContext> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessError.class);

    private final LastMessages lm;  

    public ProcessError(LastMessages lm) {
        this.lm = lm;
    }

    @Override
    public void accept(ErrorContext errorContext) {
        LOGGER.info("Error occurred in partition processor for partition {}, {}",
           errorContext.getPartitionContext().getPartitionId(),
           errorContext.getThrowable());
        lm.addMessage("Error: " + errorContext.getThrowable().getMessage() );
    }
    
}
