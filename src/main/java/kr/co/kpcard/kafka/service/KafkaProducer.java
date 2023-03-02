package kr.co.kpcard.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import kr.co.kpcard.kafka.persistence.model.KafkaMessage;
import kr.co.kpcard.kafka.persistence.repository.KafkaMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// KafkaTemplate에 Topic명과 Message를 전달
// KafkaTemplate.send() 메서드가 실행되면 Kafka 서버로 메시지가 전송됩니다.
@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    private static final String TOPIC = "exam";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaMessageRepository kafkaMessageRepository;

    public void sendMessage(String message) {
        log.info("Produce message: {}", message);

        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setMessage(message);
        kafkaMessage.setStatus("PRODUCED");
        kafkaMessageRepository.save(kafkaMessage);
        log.info("Saved message id : {}, message : {}", kafkaMessage.getId(), kafkaMessage.getMessage());

        this.kafkaTemplate.send(TOPIC, message);
    }
}
