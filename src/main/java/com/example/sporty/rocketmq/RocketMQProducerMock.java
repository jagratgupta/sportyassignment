package com.example.sporty.rocketmq;

import com.example.sporty.model.Bet;
import org.springframework.stereotype.Service;

@Service
public class RocketMQProducerMock {

    private final RocketMQConsumerMock rocketMQConsumer;

    public RocketMQProducerMock(RocketMQConsumerMock rocketMQConsumer) {
        this.rocketMQConsumer = rocketMQConsumer;
    }

    public void sendBetSettlement(Bet bet) {
        System.out.println("🚀 [MOCK] RocketMQ Producer: sending bet settlement: " + bet);
        rocketMQConsumer.consume(bet);
    }
}
