package com.example.sporty.kafka;

import com.example.sporty.model.EventOutcome;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, EventOutcome> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, EventOutcome> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEventOutcome(EventOutcome event) {
        kafkaTemplate.send("event-outcomes", event.getEventId().toString(), event);
    }
}
