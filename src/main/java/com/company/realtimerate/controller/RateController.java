package com.company.realtimerate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/rate")
public class RateController {

    @GetMapping
    public Flux<String> streamRate() {
        return Flux.interval(Duration.ofSeconds(30))
                   .map(sequence -> "Flux - " + LocalTime.now().toString());
    }
}
