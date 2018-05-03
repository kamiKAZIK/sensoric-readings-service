package com.sensoric.readings.domain.service;

import com.sensoric.readings.domain.model.DHTReading;
import com.sensoric.readings.domain.repository.DHTReadingRepository;
import lombok.Data;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class DHTReadingService extends AbstractReadingService<DHTReading, DHTReadingService.DHTValue> {
	@Autowired
	private Publisher<Message<DHTReading>> readingsPublisher;

	@Autowired
	public DHTReadingService(DHTReadingRepository repository) {
		super(repository);
	}

	public void persistReadings(Flux<DHTReading> readings) {
		Flux.from(readingsPublisher)
				.subscribe(reading -> persistReading(reading.getPayload()));
	}

	public Flux<ServerSentEvent<DHTReading>> subscribe() {
		return Flux.from(readingsPublisher)
				.map(message -> ServerSentEvent.builder(message.getPayload()).build());
	}

	@Data
	public static final class DHTValue {
		private final Float temperature, humidity;
	}
}
