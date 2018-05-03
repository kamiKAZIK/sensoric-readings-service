package com.sensoric.readings.resource;

import com.sensoric.readings.domain.model.DHTReading;
import com.sensoric.readings.domain.service.DHTReadingService;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
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

	@GetMapping(path = "/readings", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ServerSentEvent<DHTReading>> subscribe() {
		return service.subscribe();
	}
}
