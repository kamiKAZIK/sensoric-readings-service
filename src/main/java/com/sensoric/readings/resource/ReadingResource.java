package com.sensoric.readings.resource;

import com.sensoric.readings.domain.model.Reading;
import com.sensoric.readings.domain.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@RestController
public class ReadingResource {
	private final ReadingService service;

	@Autowired
	public ReadingResource(ReadingService service) {
        this.service = service;
    }

    @GetMapping(value = "test")
	public Mono<Reading> test() {
		return service.persistTemperatureAndHumidity(UUID.randomUUID(), "asdf", LocalDateTime.now());
	}

	@GetMapping(value = "test2")
	public Flux<Reading> test2() {
		return service.readTemperatureAndHumidity(
				UUID.randomUUID(),
				LocalDateTime.now().minus(3, ChronoUnit.DAYS),
				LocalDateTime.now().plus(3, ChronoUnit.DAYS)
		);
	}
}
