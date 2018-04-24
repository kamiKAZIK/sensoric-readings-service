package com.sensoric.readings.domain.service;

import com.sensoric.readings.domain.model.Reading;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.time.LocalDateTime;
import java.util.UUID;

public interface ReadingService<T extends Reading, V> {
    Flux<T> readReadings(UUID sensorId, LocalDateTime timestampFrom, LocalDateTime timestampTo);
    Mono<T> persistReading(UUID sensorId, V readingValue, LocalDateTime timestamp);
}
