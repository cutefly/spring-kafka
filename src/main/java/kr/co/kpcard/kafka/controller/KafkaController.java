package kr.co.kpcard.kafka.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.kpcard.kafka.service.KafkaProducer;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {
    private final KafkaProducer producer;

    // post 방식으로 message 데이터를 받아서 Producer 서비스로 전달
    @PostMapping
    public String sendMessage(@RequestParam("message") String message) {
        this.producer.sendMessage(message);

        return "success";
    }
}
