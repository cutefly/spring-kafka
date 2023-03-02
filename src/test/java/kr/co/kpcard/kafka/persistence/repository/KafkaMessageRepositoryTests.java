package kr.co.kpcard.kafka.persistence.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.kpcard.kafka.persistence.model.KafkaMessage;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class KafkaMessageRepositoryTests {

    @Autowired
    KafkaMessageRepository kafkaMessageRepository;

    @Test
    void findAllTest() { // 저장된 데이터 모두를 Spring JPA에 미리 구현된 findAll 명령을 통해 불러온다
        List<KafkaMessage> kafkaMessageList;

        log.info("starting test.....");
        kafkaMessageList = kafkaMessageRepository.findAll();
        for (KafkaMessage message : kafkaMessageList)
            log.debug("[FindAll]: " + message.getId() + " | " + message.getMessage());
        log.info("finished test.....");
    }

}
