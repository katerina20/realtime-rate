package com.company.realtimerate.repository;

import com.company.realtimerate.model.Rate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RateRepository extends MongoRepository<Rate, Long> {
}
