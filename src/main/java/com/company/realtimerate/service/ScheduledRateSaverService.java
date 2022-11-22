package com.company.realtimerate.service;


import com.company.realtimerate.model.Rate;
import com.company.realtimerate.repository.RateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.time.LocalDateTime.now;

@Slf4j
@Service
public class ScheduledRateSaverService {

    @Autowired
    RateRepository rateRepository;

    @Autowired
    RateAPIService rateAPIService;

    @Scheduled(fixedDelay = 2000)
    private void saveCurrentRate() {
        log.info("Starting saving current rate to db.");
        Optional<Rate> exchangeRateUsdUah = rateAPIService.getExchangeRateUsdUah();
        if (exchangeRateUsdUah.isEmpty()) return;
        rateRepository.save(new Rate(exchangeRateUsdUah.get().getRate(), now()));
    }

    @Scheduled(cron = "0 0 0 * * ?", zone = "Europe/Kiev")
    private void removeDataEachDay() {
        rateRepository.deleteAll();
    }
}
