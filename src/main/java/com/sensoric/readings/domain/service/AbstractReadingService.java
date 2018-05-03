package com.sensoric.readings.domain.service;

import com.sensoric.readings.domain.model.Reading;
import com.sensoric.readings.domain.repository.CassandraReadingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class AbstractReadingService<T extends Reading, V> implements ReadingService<T, V> {
	private final CassandraReadingRepository<T> repository;

	protected AbstractReadingService(CassandraReadingRepository<T> repository) {
		this.repository = repository;
	}

	@Override
	public Flux<T> readReadings(UUID sensorId, LocalDateTime timestampFrom, LocalDateTime timestampTo) {
		return repository.findReadings(sensorId, timestampFrom, timestampTo);
	}

	@Override
	public Mono<T> readLastReading(UUID sensorId, LocalDate dateFrom, LocalDate dateTo) {
		return repository.findLastReading(sensorId, dateFrom, dateTo);
	}

	@Override
	public Mono<T> persistReading(T reading) {
		return repository.save(reading);
	}
}
