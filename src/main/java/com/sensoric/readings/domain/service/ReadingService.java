package com.sensoric.readings.domain.service;

import com.sensoric.readings.domain.model.HumidityReading;
import com.sensoric.readings.domain.model.TemperatureReading;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ReadingService {
    TemperatureReading readTemperature(String sensorId, LocalDateTime timestampFrom, LocalDateTime timesTo);
    HumidityReading readHumidity(String sensorId, LocalDateTime timestampFrom, LocalDateTime timesTo);
    void persistTemperature(String sensorId, Float temperature, LocalDateTime timestamp);
    void persistHumidity(String sensorId, Float humidity, LocalDateTime timestamp);
}
