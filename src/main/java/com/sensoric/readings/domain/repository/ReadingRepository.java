package com.sensoric.readings.domain.repository;

import com.sensoric.readings.domain.model.Reading;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface ReadingRepository<V, T extends Reading<V>> extends ReactiveMongoRepository<T, UUID> {
    Flux<T> findBySensorId(String sensorId);
}
