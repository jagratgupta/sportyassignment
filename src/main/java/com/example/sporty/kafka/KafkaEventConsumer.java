package com.example.sporty.kafka;

import com.example.sporty.model.Bet;
import com.example.sporty.model.EventOutcome;
import com.example.sporty.repository.BetRepository;
import com.example.sporty.rocketmq.RocketMQProducerMock;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaEventConsumer {

    private final BetRepository betRepository;
    private final RocketMQProducerMock rocketMQProducer;

    public KafkaEventConsumer(BetRepository betRepository, RocketMQProducerMock rocketMQProducer) {
        this.betRepository = betRepository;
        this.rocketMQProducer = rocketMQProducer;
    }

    @KafkaListener(topics = "event-outcomes", groupId = "betting-group")
    public void consume(EventOutcome event) {
        List<Bet> bets = betRepository.findByEventId(event.getEventId());
        for (Bet bet : bets) {
            if (bet.getEventWinnerId().equals(event.getEventWinnerId())) {
                rocketMQProducer.sendBetSettlement(bet);
            }
        }
    }
}
