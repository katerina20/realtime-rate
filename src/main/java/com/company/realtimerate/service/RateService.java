package com.company.realtimerate.service;

import com.company.realtimerate.model.Rate;
import com.company.realtimerate.repository.RateRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@Log4j2
@Service
public class RateService {

    private static final String RATE_STRING_FORMAT = "Текущий курс: %.2f Средний курс: %.2f Дата: %s";

    private static final String DATETIME_PATTERN = "dd.MM.yyyy HH:mm:ss";

    @Autowired
    RateRepository rateRepository;

    public String getRateString() {
        List<Rate> rates = rateRepository.findAll();
        return String.format(RATE_STRING_FORMAT,
                getLastRate(rates),
                getAverageRate(rates),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATETIME_PATTERN)));
    }

    private double getLastRate(List<Rate> rates) {
        log.info("Starting getting the latest rate from db.");
        Optional<Rate> latestRate = rates.stream().max(Comparator.comparing(Rate::getDateTime));
        return latestRate.map(Rate::getRate).orElse(0.0);
    }

    private double getAverageRate(List<Rate> rates) {
        log.info("Starting getting the average rate from db.");
        double sum = rates.stream().flatMapToDouble(rate -> DoubleStream.of(rate.getRate())).sum();
        return sum / rates.size();
    }
}
