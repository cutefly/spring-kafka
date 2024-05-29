package kr.co.kpcard.kafka.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.kpcard.kafka.persistence.model.KafkaDeadLetter;

public interface KafkaDeadLetterRepository extends JpaRepository<KafkaDeadLetter, Long> {
}