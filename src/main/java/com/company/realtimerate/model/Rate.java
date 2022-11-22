package com.company.realtimerate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("rates")
@NoArgsConstructor
@RequiredArgsConstructor
public class Rate {

    @Id
    private String id;

    @NonNull
    @JsonProperty("conversion_rate")
    private double rate;

    @NonNull
    private LocalDateTime dateTime;
}
