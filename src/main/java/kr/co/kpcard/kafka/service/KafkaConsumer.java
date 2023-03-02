package kr.co.kpcard.kafka.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import kr.co.kpcard.kafka.persistence.model.KafkaMessage;
import kr.co.kpcard.kafka.persistence.repository.KafkaMessageRepository;
import lombok.extern.slf4j.Slf4j;

// exam이라는 Topic에서 consumer의 group id가 foo로 데이터를 받아옴
@Service
@Slf4j
public class KafkaConsumer {

    @Autowired
    private KafkaMessageRepository kafkaMessageRepository;

    @KafkaListener(topics = "exam", groupId = "foo")
    public void consume(String id) throws IOException {
        Optional<KafkaMessage> kafkaMessage = kafkaMessageRepository.findById(Long.parseLong(id));

        kafkaMessage.ifPresent(m -> {
            m.setStatus("CONSUMED");
            kafkaMessageRepository.save(m);
            log.info("Consumed message : {}", m.getMessage());
        });
    }
}
