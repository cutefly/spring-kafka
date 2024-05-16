package kr.co.kpcard.kafka.service;

import java.io.IOException;
import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import kr.co.kpcard.kafka.model.Item;
import kr.co.kpcard.kafka.persistence.model.KafkaMessage;
import kr.co.kpcard.kafka.persistence.repository.KafkaMessageRepository;
import lombok.extern.slf4j.Slf4j;

// exam이라는 Topic에서 consumer의 group id가 foo로 데이터를 받아옴
@Service
@Slf4j
public class KafkaConsumer {

    @Autowired
    private KafkaMessageRepository kafkaMessageRepository;

    @KafkaListener(topics = "chris.study.mytopic", groupId = "foo")
    public void consume(ConsumerRecord<String, Item> consumerRecord) throws IOException {
        log.info("Consumer reconde topic : {}, offset : {}, message : {}",
                consumerRecord.topic(),
                consumerRecord.offset(),
                consumerRecord.value());

        Item item = consumerRecord.value();
        Optional<KafkaMessage> kafkaMessage = kafkaMessageRepository.findById(item.getId());

        kafkaMessage.ifPresent(m -> {
            m.setStatus("CONSUMED");
            kafkaMessageRepository.save(m);
            log.info("Processed message : {}", m.getMessage());
        });
    }
}
