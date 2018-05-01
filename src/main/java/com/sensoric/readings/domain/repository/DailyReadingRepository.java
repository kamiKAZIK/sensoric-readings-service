package com.sensoric.readings.domain.repository;

import org.springframework.data.repository.NoRepositoryBean;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.UUID;

@NoRepositoryBean
public interface DailyReadingRepository<T> {
    Flux<T> findReadings(UUID sensorId, LocalDateTime timestampFrom, LocalDateTime timestampTo);
}
