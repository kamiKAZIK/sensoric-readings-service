package com.sensoric.readings.domain.service;

import com.sensoric.readings.domain.model.DHTReading;
import com.sensoric.readings.domain.model.Reading;
import com.sensoric.readings.domain.repository.DHTReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Service
public class DHTReadingService implements ReadingService<DHTReading> {
	private final DHTReadingRepository repository;

	@Autowired
	public DHTReadingService(DHTReadingRepository repository) {
		this.repository = repository;
	}

	@Override
	public Flux<DHTReading> readReadings(UUID sensorId, LocalDateTime timestampFrom, LocalDateTime timestampTo) {
		return repository.findAll();
	}

	@Override
	public Mono<DHTReading> persistReading(UUID sensorId, String readingValue, LocalDateTime timestamp) {
		DHTReading reading = new DHTReading();
		reading.setKey(buildKey(sensorId, timestamp));
		reading.setTemperature(20.0f);
		reading.setHumidity(55.0f);

		return repository.save(reading);
	}
}
