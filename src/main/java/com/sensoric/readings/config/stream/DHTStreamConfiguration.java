package com.sensoric.readings.config.stream;

import com.sensoric.readings.domain.service.DHTReadingService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.SubscribableChannel;

import java.time.LocalDateTime;
import java.util.UUID;

@EnableBinding(DHTStreamConfiguration.InputBinding.class)
@Configuration
public class DHTStreamConfiguration {
	private final DHTReadingService service;

	@Autowired
	public DHTStreamConfiguration(DHTReadingService service) {
		this.service = service;
	}

	@StreamListener(DHTStreamConfiguration.InputBinding.INPUT)
	public void handle(MMM message) {
		service.persistReading(
				message.getSensorId(),
				new DHTReadingService.DHTValue(
						message.getTemperature(),
						message.getHumidity()
				),
				LocalDateTime.now()
		);
	}

	@Data
	public static final class MMM {
		private UUID sensorId;
		private Float temperature, humidity;
	}

	public interface InputBinding {
		String INPUT = "sensor.dht.input";

		@Input(DHTStreamConfiguration.InputBinding.INPUT)
		SubscribableChannel input();
	}
}
