package kr.co.kpcard.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Kafka item
 * Serialize, Deserialize를 위해서는 @AllArgsConstructor, @NoArgsConstructor 필요
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private Long id;

    @NonNull
    private String type;

    @NonNull
    private String key;

    @NonNull
    private String action;

    private String message;
}
