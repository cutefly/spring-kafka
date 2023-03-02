package kr.co.kpcard.kafka.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.kpcard.kafka.persistence.model.KafkaMessage;

@Repository
public interface KafkaMessageRepository extends JpaRepository<KafkaMessage, Long> {
}