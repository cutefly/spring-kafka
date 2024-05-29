package kr.co.kpcard.kafka.service;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import kr.co.kpcard.kafka.model.Item;
import kr.co.kpcard.kafka.persistence.model.KafkaMessage;
import kr.co.kpcard.kafka.persistence.repository.KafkaMessageRepository;
import kr.co.kpcard.kafka.persistence.model.KafkaDeadLetter;
import kr.co.kpcard.kafka.persistence.repository.KafkaDeadLetterRepository;
import lombok.extern.slf4j.Slf4j;

// exam이라는 Topic에서 consumer의 group id가 foo로 데이터를 받아옴
@Service
@Slf4j
public class KafkaConsumer {

    @Autowired
    private KafkaMessageRepository kafkaMessageRepository;

    @Autowired
    private KafkaDeadLetterRepository kafkaDeadLetterRepository;

    Random random = new Random();

    @RetryableTopic(include = { NullPointerException.class,
            ArrayIndexOutOfBoundsException.class }, autoCreateTopics = "false", attempts = "4", backoff = @Backoff(delay = 1000, multiplier = 2), topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE, retryTopicSuffix = "-custom-try", dltTopicSuffix = "-dead-t")
    @KafkaListener(topics = "chris.study.mytopic")
    public void consume(ConsumerRecord<String, Item> consumerRecord)
            throws IOException {
        log.info("Consumer record topic : {}, offset : {}, message : {}",
                consumerRecord.topic(),
                consumerRecord.offset(),
                consumerRecord.value());

        Item item = null;

        if (consumerRecord.topic().contains("chris.study.mytopic-custom-try-")) {
            String matchTopic = String.format("chris.study.mytopic-custom-try-%d", random.nextInt(2));

            log.info("current topic : {}, match topic : {}", consumerRecord.topic(), matchTopic);
            if (consumerRecord.topic().equals(matchTopic)) {
                item = consumerRecord.value();
            }
            Optional<KafkaMessage> kafkaMessage = kafkaMessageRepository.findById(item.getId()); // NullPointException

            kafkaMessage.ifPresent(m -> {
                m.setStatus("CONSUMED");
                kafkaMessageRepository.save(m);
                log.info("Processed message : {}", m.getMessage());
            });
        } else {
            /*
             * item = consumerRecord.value();
             * Optional<KafkaDeadLetter> kafkaDeadLetter =
             * kafkaDeadLetterRepository.findById(item.getId());
             * 
             * kafkaDeadLetter.ifPresent(m -> {
             * m.setStatus("CONSUMED");
             * kafkaDeadLetterRepository.save(m);
             * log.info("Processed dead letter : {}", m.getMessage());
             * });
             */
            log.error("Processed dead letter : {}", item.getId());
        }
    }

    @KafkaListener(topics = "chris.study.mytopic-dead-t")
    public void consumeDeadLetter(ConsumerRecord<String, Item> consumerRecord) throws IOException {
        log.info("Consumer dead letter topic : {}, offset : {}, message : {}",
                consumerRecord.topic(),
                consumerRecord.offset(),
                consumerRecord.value());

        Item item = null;

        item = consumerRecord.value();
        KafkaDeadLetter kafkaDeadLetter = new KafkaDeadLetter();

        kafkaDeadLetter.setId(item.getId());
        kafkaDeadLetter.setItemType(item.getType());
        kafkaDeadLetter.setItemKey(item.getKey());
        kafkaDeadLetter.setItemAction(item.getAction());
        kafkaDeadLetter.setMessage(item.getMessage());
        kafkaDeadLetter.setStatus("DEAD");

        kafkaDeadLetterRepository.save(kafkaDeadLetter);
        log.error("Processed dead letter : {}", kafkaDeadLetter.getId());
    }
}
