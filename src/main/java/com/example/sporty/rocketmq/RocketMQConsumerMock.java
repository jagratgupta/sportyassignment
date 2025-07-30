package com.example.sporty.rocketmq;

import com.example.sporty.model.Bet;
import org.springframework.stereotype.Service;

@Service
public class RocketMQConsumerMock {
    public void consume(Bet bet) {
        System.out.println("[MOCK-RocketMQ Consumer] Bet Settled: " + bet.getBetId() +
                ", Event ID: " + bet.getEventId() +
                ", Winner ID: " + bet.getEventWinnerId() +
                ", Amount: " + bet.getBetAmount() +
                ", User ID: " + bet.getUserId());
    }
}
