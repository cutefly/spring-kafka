package kr.co.kpcard.kafka.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

// KafkaTemplate에 Topic명과 Message를 전달
// KafkaTemplate.send() 메서드가 실행되면 Kafka 서버로 메시지가 전송됩니다.
@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private static final String TOPIC = "exam";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        System.out.println(String.format("Produce message: %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }
}
