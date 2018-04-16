package com.sensoric.readings.domain.service;

import com.sensoric.readings.domain.model.Reading;
import com.sensoric.readings.domain.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DefaultReadingService implements ReadingService {
	private final ReadingRepository readingRepository;

	@Autowired
	public DefaultReadingService(ReadingRepository readingRepository) {
		this.readingRepository = readingRepository;
	}

	@Override
	public Flux<Reading> readTemperatureAndHumidity(UUID sensorId, LocalDateTime timestampFrom, LocalDateTime timestampTo) {
		return readingRepository.findAll();
	}

	@Override
	public Mono<Reading> persistTemperatureAndHumidity(UUID sensorId, String readingValue, LocalDateTime timestamp) {
		Reading reading = new Reading();
		reading.setSensorId(sensorId);
		reading.setValue(readingValue);
		reading.setTimestamp(timestamp);

		return readingRepository.save(reading);
	}
}
