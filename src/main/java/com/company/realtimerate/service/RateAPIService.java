package com.company.realtimerate.service;

import com.company.realtimerate.model.Rate;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Log4j2
@Service
public class RateAPIService {

    private static final String URL_TEMPLATE = "https://v6.exchangerate-api.com/v6/%s/pair/USD/UAH";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${API_KEY:0}")
    private String apiKey;

    public Optional<Rate> getExchangeRateUsdUah() {
        log.info("Start connecting to API to get current rate.");
        return Try.of(() -> restTemplate.getForObject(String.format(URL_TEMPLATE, apiKey), Rate.class))
                  .onFailure(e -> log.error(e.getLocalizedMessage())).toJavaOptional();
    }
}
