package com.sensoric.readings.domain.repository;

import com.sensoric.readings.domain.model.Reading;
import org.springframework.data.repository.NoRepositoryBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@NoRepositoryBean
public interface ReadingRepository<K, T extends Reading<K>> {
    Flux<T> findReadings(UUID sensorId, LocalDateTime timestampFrom, LocalDateTime timestampTo);
    Mono<T> findLastReading(UUID sensorId, LocalDate dateFrom, LocalDate dateTo);
}
