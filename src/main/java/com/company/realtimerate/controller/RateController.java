package com.company.realtimerate.controller;

import com.company.realtimerate.service.RateService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Log4j2
@RestController
@RequestMapping("/api/rate")
public class RateController {

    @Autowired
    RateService rateService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamRate() {
        log.info("Start retrieving rate stream.");
        Mono<String> first = Mono.just(1).map(s -> rateService.getRateString());
        Flux<String> rest = Flux.interval(Duration.ofSeconds(30))
                                .map(sequence -> rateService.getRateString());
        return Flux.concat(first, rest);
    }
}
