package com.company.realtimerate.service;

import com.company.realtimerate.model.Rate;
import com.company.realtimerate.repository.RateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RateServiceTest {

    @Mock
    RateRepository rateRepository;

    @InjectMocks
    RateService rateService;

    @Test
    public void whenDataExists_thenReturnInfoString() {
        List<Rate> rateList = Arrays.asList(
                new Rate(31.2, LocalDateTime.now()),
                new Rate(31.0, LocalDateTime.now().minus(5, ChronoUnit.SECONDS)));
        when(rateRepository.findAll()).thenReturn(rateList);

        assertThat(rateService.getRateString()).contains("Текущий курс: 31,20 Средний курс: 31,10");
    }

    @Test
    public void whenDataNotExists_thenReturnNoDataString() {
        List<Rate> rateList = new ArrayList<>();
        when(rateRepository.findAll()).thenReturn(rateList);

        Assertions.assertEquals("No data.", rateService.getRateString());
    }

}