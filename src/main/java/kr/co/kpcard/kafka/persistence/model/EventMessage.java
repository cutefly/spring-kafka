package kr.co.kpcard.kafka.persistence.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "event_message")
@DynamicUpdate
public class EventMessage {
    @Id
    @Column(name = "id")
    private String id;

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
