package kr.co.kpcard.kafka.persistence.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

// @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "kafka_message")
@DynamicUpdate
public class KafkaMessage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "item_type")
    private String itemType;

    @Setter
    @Column(name = "item_key")
    private String itemKey;

    @Setter
    @Column(name = "message")
    private String message;

    @Setter
    @Column(name = "status")
    private String status;

    @Setter
    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Setter
    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;
}
