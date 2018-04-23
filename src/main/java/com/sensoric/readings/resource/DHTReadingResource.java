package com.sensoric.readings.resource;

import com.sensoric.readings.domain.model.DHTReading;
import com.sensoric.readings.domain.model.Reading;
import com.sensoric.readings.domain.service.DHTReadingService;
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
public class DHTReadingResource {
	private final DHTReadingService service;

	@Autowired
	public DHTReadingResource(DHTReadingService service) {
        this.service = service;
    }

    @GetMapping(value = "test")
	public Mono<DHTReading> test() {
		return service.persistReading(
				UUID.randomUUID(),
				new DHTReadingService.DHTValue(20.0f, 87.0f),
				LocalDateTime.now()
		);
	}

	@GetMapping(value = "test2")
	public Flux<DHTReading> test2() {
		return service.readReadings(
				UUID.randomUUID(),
				LocalDateTime.now().minus(3, ChronoUnit.DAYS),
				LocalDateTime.now().plus(3, ChronoUnit.DAYS)
		);
	}
}
