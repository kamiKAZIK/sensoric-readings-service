package com.sensoric.readings.domain.service;

import com.sensoric.readings.domain.model.Reading;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ReadingService {
    Flux<Reading> readTemperatureAndHumidity(UUID sensorId, LocalDateTime timestampFrom, LocalDateTime timestampTo);
    Mono<Reading> persistTemperatureAndHumidity(UUID sensorId, String readingValue, LocalDateTime timestamp);
}
