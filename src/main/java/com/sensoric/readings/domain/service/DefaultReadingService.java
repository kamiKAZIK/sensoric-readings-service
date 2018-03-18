package com.sensoric.readings.domain.service;

import com.sensoric.readings.domain.model.HumidityReading;
import com.sensoric.readings.domain.model.Reading;
import com.sensoric.readings.domain.model.TemperatureReading;
import com.sensoric.readings.domain.repository.HumidityReadingRepository;
import com.sensoric.readings.domain.repository.TemperatureReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@Service
public class DefaultReadingService implements ReadingService {
    private final TemperatureReadingRepository temperatureRepository;
    private final HumidityReadingRepository humidityRepository;

    @Autowired
    public DefaultReadingService(TemperatureReadingRepository temperatureRepository,
                                 HumidityReadingRepository humidityRepository) {
        this.temperatureRepository = temperatureRepository;
        this.humidityRepository = humidityRepository;
    }

    @Override
    public TemperatureReading readTemperature(String sensorId, LocalDateTime timestampFrom, LocalDateTime timesTo) {
        return null;
    }

    @Override
    public HumidityReading readHumidity(String sensorId, LocalDateTime timestampFrom, LocalDateTime timesTo) {
        return null;
    }

    @Override
    public void persistTemperature(String sensorId, Float temperature, LocalDateTime timestamp) {
        TemperatureReading reading = new TemperatureReading();
        reading.setId(UUID.randomUUID().toString());
        reading.setSensorId(sensorId);
        temperatureRepository.save(reading).subscribe();

//        temperatureRepository.findBySensorId(sensorId)
//                .switchIfEmpty(Mono.just(reading))
//                .map(r -> fillEntries(r, temperature, timestamp))
//                .flatMap(r -> temperatureRepository.save(r));
    }

    @Override
    public void persistHumidity(String sensorId, Float humidity, LocalDateTime timestamp) {
        humidityRepository.findBySensorId(sensorId)
                .map(r -> fillEntries(r, humidity, timestamp))
                .flatMap(r -> humidityRepository.save(r));
    }

    private <T, X extends Reading<T>> X fillEntries(X reading,
                                                    T value,
                                                    LocalDateTime timestamp) {
        reading.getEntries()
                .putIfAbsent(timestamp.toLocalDate(), new HashMap<>())
                .putIfAbsent(timestamp.getHour(), new HashMap<>())
                .putIfAbsent(timestamp.getMinute(), new HashMap<>())
                .putIfAbsent(timestamp.getSecond(), value);

        return reading;
    }
}
