package com.sensoric.readings.domain.service;

import com.sensoric.readings.domain.model.DHTReading;
import com.sensoric.readings.domain.model.DailyReading;
import com.sensoric.readings.domain.repository.DHTReadingRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DHTReadingService implements ReadingService<DHTReading, DHTReadingService.DHTValue> {
	private final DHTReadingRepository repository;

	@Autowired
	public DHTReadingService(DHTReadingRepository repository) {
		this.repository = repository;
	}

	@Override
	public Flux<DHTReading> readReadings(UUID sensorId, LocalDateTime timestampFrom, LocalDateTime timestampTo) {
		return repository.findReadings(sensorId, timestampFrom, timestampTo);
	}

	@Override
	public Mono<DHTReading> persistReading(UUID sensorId, DHTValue readingValue, LocalDateTime timestamp) {
		return repository.save(new DHTReading(
				new DailyReading.DailyKey(
						sensorId,
						timestamp.toLocalDate(),
						timestamp.toLocalTime().toNanoOfDay()
				),
				readingValue.getTemperature(),
				readingValue.getHumidity()
		));
	}

	@Data
	public static final class DHTValue {
		private final Float temperature, humidity;
	}
}
