package com.sensoric.readings.resource;

import com.sensoric.readings.domain.model.DHTReading;
import com.sensoric.readings.domain.service.DHTReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@RequestMapping(path = "/dht")
@RestController
public class DHTReadingResource {
	private final DHTReadingService service;

	@Autowired
	public DHTReadingResource(DHTReadingService service) {
        this.service = service;
    }

    @PostMapping
	public Mono<DHTReading> save(
			@RequestParam UUID sensorId,
			@RequestBody DHTReadingService.DHTValue value
	) {
		return service.persistReading(
				sensorId,
				value,
				LocalDateTime.now()
		);
	}

	@GetMapping
	public Flux<DHTReading> search(
			@RequestParam UUID sensorId,
			@RequestParam LocalDateTime timestampFrom,
			@RequestParam LocalDateTime timestampTo
	) {
		return service.readReadings(
				sensorId,
				timestampFrom,
				timestampTo
		);
	}
}
