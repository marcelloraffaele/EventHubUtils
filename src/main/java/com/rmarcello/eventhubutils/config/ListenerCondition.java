package com.rmarcello.eventhubutils.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ListenerCondition implements Condition{

    private static final String PROP_ENABLE = "eventhub.listener.enabled";

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        // Retrieve the property value from application.properties
        String propertyValue = context.getEnvironment().getProperty(PROP_ENABLE);

        // Check if the property value is "true"
        return "true".equalsIgnoreCase(propertyValue);
        
    }
    
}
