package com.company.realtimerate.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Document("rates")
@RequiredArgsConstructor
public class Rate {

    @Id
    private String id;

    @NonNull
    private double rate;

    @NonNull
    private LocalDateTime dateTime;
}
