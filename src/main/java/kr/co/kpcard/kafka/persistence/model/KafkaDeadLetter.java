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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

// @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "kafka_dead_letter")
@DynamicUpdate
public class KafkaDeadLetter {
    @Id
    @Setter
    @NonNull
    @Column(name = "id")
    private Long id;

    @Setter
    @NonNull
    @Column(name = "item_type")
    private String itemType;

    @Setter
    @NonNull
    @Column(name = "item_key")
    private String itemKey;

    @Setter
    @NonNull
    @Column(name = "item_action")
    private String itemAction;

    @Setter
    @Column(name = "message")
    private String message;

    @Setter
    @NonNull
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
