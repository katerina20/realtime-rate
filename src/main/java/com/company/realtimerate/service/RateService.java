package com.company.realtimerate.service;

import com.company.realtimerate.model.Rate;
import com.company.realtimerate.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@Service
public class RateService {

    private static final String RATE_STRING_FORMAT = "Текущий курс: %s Средний курс: %s Дата: %s";

    @Autowired
    RateRepository rateRepository;

    public String getRateString() {
        List<Rate> rates = rateRepository.findAll();
        return String.format(RATE_STRING_FORMAT, getLastRate(rates), getAverageRate(rates), LocalDateTime.now());
    }

    private double getLastRate(List<Rate> rates) {
        Optional<Rate> latestRate = rates.stream().max(Comparator.comparing(Rate::getDateTime));
        return latestRate.map(Rate::getRate).orElse(0.0);
    }

    private double getAverageRate(List<Rate> rates) {
        double sum = rates.stream().flatMapToDouble(rate -> DoubleStream.of(rate.getRate())).sum();
        return sum / rates.size();
    }
}
