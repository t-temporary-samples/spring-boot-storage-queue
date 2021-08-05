package com.samples.springboot;

import com.azure.spring.integration.storage.queue.StorageQueueOperation;
import com.azure.spring.integration.storage.queue.inbound.StorageQueueMessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReceiveController {
    private static final String QUEUE = "example";
    private static final String CHANNEL = "test";
    private List<String> messages = new ArrayList<>();

    @Bean
    @InboundChannelAdapter(channel = CHANNEL, poller = @Poller(fixedDelay = "1000"))
    public StorageQueueMessageSource storageQueueMessageSource(StorageQueueOperation storageQueueOperation) {
        return new StorageQueueMessageSource(QUEUE, storageQueueOperation);
    }

    @ServiceActivator(inputChannel = CHANNEL)
    public void messageReceiver(byte[] payload) {
        messages.add(new String(payload));
        System.out.println("Receive: " + new String(payload));
    }

    @RequestMapping("/get")
    public List<String> getMessages() {
        return messages;
    }
}
