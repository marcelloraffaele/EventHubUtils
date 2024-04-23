package com.rmarcello.eventhubutils.consumer;

import java.util.LinkedList;

public class LastMessages {
    private final LinkedList<String> lastMessages;
    private final int maxSize;

    public LastMessages(int maxSize) {
        this.maxSize = maxSize;
        this.lastMessages = new LinkedList<>();
    }

    public LinkedList<String> getLastMessages() {
        return lastMessages;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void addMessage(String m) {
        if(lastMessages.size() == maxSize)
            lastMessages.removeFirst();
        lastMessages.add(m);
    }

    @Override
    public String toString() {
        return "LastMessages [lastMessages=" + lastMessages + ", maxSize=" + maxSize + "]";
    }

    
}
