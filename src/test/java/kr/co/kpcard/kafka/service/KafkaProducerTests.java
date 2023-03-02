package kr.co.kpcard.kafka.service;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
public class KafkaProducerTests {
    @Autowired
    private KafkaProducer kafkaProducer;

    @Test
    public void sendMessageTest() {
        kafkaProducer
                .sendMessage(String.format("Message generated : %1$tb %1$te, %1$tY %1$tI:%1$tM %1$Tp", new Date()));
    }
}
