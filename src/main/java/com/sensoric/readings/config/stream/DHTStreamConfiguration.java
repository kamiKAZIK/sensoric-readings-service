package com.sensoric.readings.config.stream;

import com.sensoric.readings.config.flow.DHTReadingsFlow;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import reactor.core.publisher.Flux;

import java.util.UUID;

@EnableBinding(DHTStreamConfiguration.InputBinding.class)
@Configuration
public class DHTStreamConfiguration {
	private final MessageChannel channel;

	@Autowired
	public DHTStreamConfiguration(
			@Qualifier(value = DHTReadingsFlow.FLOW_INPUT) MessageChannel channel
	) {
		this.channel = channel;
	}

	@StreamListener
	public void handle(@Input(value = InputBinding.INPUT) Flux<DHTMessage> messages) {
		messages.subscribe(message -> channel
				.send(MessageBuilder.withPayload(message)
						.build())
		);
	}

	@Data
	public static final class DHTMessage {
		private UUID sensorId;
		private Float temperature, humidity;
	}

	public interface InputBinding {
		String INPUT = "sensor.dht.input";

		@Input(value = InputBinding.INPUT)
		SubscribableChannel input();
	}
}
