package com.sensoric.readings.domain.service;

import com.mongodb.client.result.UpdateResult;
import com.sensoric.readings.domain.model.Reading;
import com.sensoric.readings.domain.model.TemperatureAndHumidityReading;
import com.sensoric.readings.domain.model.TemperatureAndHumidityReading.ReadingValue;
import com.sensoric.readings.domain.repository.TemperatureAndHumidityReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class DefaultReadingService implements ReadingService {
	private final TemperatureAndHumidityReadingRepository temperatureAndHumidityReadingRepository;

	@Autowired
	public DefaultReadingService(TemperatureAndHumidityReadingRepository temperatureAndHumidityReadingRepository) {
		this.temperatureAndHumidityReadingRepository = temperatureAndHumidityReadingRepository;
	}

	@Override
	public Mono<TemperatureAndHumidityReading> readTemperatureAndHumidity(String sensorId, LocalDateTime timestampFrom, LocalDateTime timestampTo) {
		//return temperatureAndHumidityReadingRepository.findReadingsBetweenDates(sensorId, timestampFrom, timestampTo);
		return null;
	}

	@Override
	public Mono<TemperatureAndHumidityReading> persistTemperatureAndHumidity(String sensorId, ReadingValue readingValue, LocalDateTime timestamp) {
		return temperatureAndHumidityReadingRepository.findBySensorId(sensorId)
				.switchIfEmpty(temperatureAndHumidityReadingRepository.save(fillEntries(new TemperatureAndHumidityReading(sensorId), readingValue, timestamp)))
				.map(reading -> fillEntries(reading, readingValue, timestamp))
				.flatMap(reading -> temperatureAndHumidityReadingRepository.save(reading));
	}

	private TemperatureAndHumidityReading fillEntries(
			TemperatureAndHumidityReading reading,
			TemperatureAndHumidityReading.ReadingValue value,
			LocalDateTime timestamp
	) {
		reading.getEntries()
				.computeIfAbsent(timestamp.toLocalDate().toString(), f -> new HashMap<>())
				.computeIfAbsent(timestamp.getHour(), f -> new HashMap<>())
				.computeIfAbsent(timestamp.getMinute(), f -> new HashMap<>())
				.putIfAbsent(timestamp.getSecond(), value);

		return reading;
	}
}
