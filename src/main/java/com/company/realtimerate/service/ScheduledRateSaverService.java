package com.company.realtimerate.service;


import com.company.realtimerate.model.Rate;
import com.company.realtimerate.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class ScheduledRateSaverService {

    @Autowired
    RateRepository rateRepository;

    @Scheduled(fixedDelay = 2000)
    private void saveCurrentRate() {
        rateRepository.save(new Rate(34.4 + LocalTime.now().getSecond(), LocalDateTime.now()));
    }
}
