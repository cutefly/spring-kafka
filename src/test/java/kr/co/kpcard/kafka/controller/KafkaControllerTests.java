package kr.co.kpcard.kafka.controller;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.kpcard.kafka.model.Item;

@SpringBootTest
public class KafkaControllerTests {

    @Autowired
    private KafkaController kafkaController;

    @Test
    public void sendMessageTest() {
        kafkaController
                .sendMessage(String.format("Message generated : %1$tb %1$te, %1$tY %1$tI:%1$tM %1$Tp", new Date()));
    }

    @Test
    public void sendItemTest() {
        Item item = Item.builder()
                .type("palrago")
                .key("20230303084000")
                .action("CREATED")
                .message("2nd message")
                .build();

        kafkaController
                .sendItem(item);
    }

}
