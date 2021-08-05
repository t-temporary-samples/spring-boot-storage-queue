package com.samples.springboot;

import com.azure.spring.integration.core.api.reactor.DefaultMessageHandler;
import com.azure.spring.integration.storage.queue.StorageQueueOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {
    private static final String QUEUE = "example";
    private static final String CHANNEL = "test";

    @Bean
    @ServiceActivator(inputChannel = CHANNEL)
    public MessageHandler messageSender(StorageQueueOperation storageQueueOperation) {
        return new DefaultMessageHandler(QUEUE, storageQueueOperation);
    }

    @MessagingGateway(defaultRequestChannel = CHANNEL)
    public interface StorageQueueGateway {
        void send(String message);
    }

    @Autowired
    StorageQueueGateway gateway;

    @RequestMapping("/send")
    public String send(@RequestParam String message) {
        gateway.send(message);
        return message;
    }
}
