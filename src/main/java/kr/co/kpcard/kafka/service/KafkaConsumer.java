package kr.co.kpcard.kafka.service;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

// exam이라는 Topic에서 consumer의 group id가 foo로 데이터를 받아옴
@Service
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "exam", groupId = "foo")
    public void consume(String message) throws IOException {
        log.info("Consumed message: {}", message);
    }
}
