package com.sensoric.readings.domain.service;

import com.sensoric.readings.domain.model.TemperatureAndHumidityReading;
import com.sensoric.readings.domain.model.TemperatureAndHumidityReading.ReadingValue;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface ReadingService {
    Mono<TemperatureAndHumidityReading> readTemperatureAndHumidity(String sensorId, LocalDateTime timestampFrom, LocalDateTime timestampTo);
    Mono<TemperatureAndHumidityReading> persistTemperatureAndHumidity(String sensorId, ReadingValue readingValue, LocalDateTime timestamp);
}
