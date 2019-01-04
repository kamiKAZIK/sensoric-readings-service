package com.sensoric.readings.domain.service;

import com.sensoric.readings.domain.command.FetchLastReadingCommand;
import com.sensoric.readings.domain.command.FetchReadingsCommand;
import com.sensoric.readings.domain.command.RegisterDHTReadingCommand;
import com.sensoric.readings.domain.dto.FetchDHTReadingsDTO;
import com.sensoric.readings.domain.dto.FetchLastDHTReadingDTO;
import com.sensoric.readings.domain.model.DHTReading;
import com.sensoric.readings.domain.model.DailyReading;
import com.sensoric.readings.domain.repository.CassandraReadingRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
class DefaultDHTReadingService implements DHTReadingService {
	private final CassandraReadingRepository<DHTReading.DailyKey, DHTReading> repository;

	DefaultDHTReadingService(CassandraReadingRepository<DailyReading.DailyKey, DHTReading> repository) {
		this.repository = repository;
	}


	@Override
	public Flux<FetchDHTReadingsDTO> fetchReadings(FetchReadingsCommand command) {
		return repository.findReadings(
				command.getSensorId(),
				command.getTimestampFrom(),
				command.getTimestampTo()
		).map(r -> new FetchDHTReadingsDTO(
				LocalDateTime.of(r.getKey().getWriteDate(), r.getKey().getWriteTime()),
				r.getTemperature(),
				r.getHumidity()
		));
	}

	@Override
	public Mono<FetchLastDHTReadingDTO> fetchLastReading(FetchLastReadingCommand command) {
		return repository.findLastReading(
				command.getSensorId(),
				command.getDateFrom(),
				command.getDateTo()
		).map(r -> new FetchLastDHTReadingDTO(
				LocalDateTime.of(r.getKey().getWriteDate(), r.getKey().getWriteTime()),
				r.getTemperature(),
				r.getHumidity()
		));
	}

	@Override
	public Mono<RegisterDHTReadingCommand> registerReading(RegisterDHTReadingCommand command) {
		return null;
	}
}
