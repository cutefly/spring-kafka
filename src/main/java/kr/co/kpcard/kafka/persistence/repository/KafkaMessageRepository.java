package kr.co.kpcard.kafka.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.kpcard.kafka.persistence.model.KafkaMessage;

public interface KafkaMessageRepository extends JpaRepository<KafkaMessage, Long> {
}