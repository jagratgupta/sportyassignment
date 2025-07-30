package com.example.sporty.controller;

import com.example.sporty.kafka.KafkaProducerService;
import com.example.sporty.model.EventOutcome;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/events")
public class EventController {

    private final KafkaProducerService kafkaProducerService;

    public EventController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    public ResponseEntity<String> publishEvent(@RequestBody EventOutcome event) {
        kafkaProducerService.sendEventOutcome(event);
        return ResponseEntity.ok("Event published");
    }
}
