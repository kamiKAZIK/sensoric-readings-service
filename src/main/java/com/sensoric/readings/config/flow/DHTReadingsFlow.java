package com.sensoric.readings.config.flow;

import com.sensoric.readings.config.stream.DHTStreamConfiguration.DHTMessage;
import com.sensoric.readings.domain.model.DHTReading;
import com.sensoric.readings.domain.model.DailyReading.DailyKey;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import java.time.LocalDateTime;

@Configuration
public class DHTReadingsFlow {
	public static final String FLOW_INPUT = "dhtReadingsFlowInput";

	@Bean
	public Publisher<Message<DHTReading>> dhtReadingsPublisher() {
		return IntegrationFlows.from(dhtReadingsFlowInput())
				.<DHTMessage, DHTReading>transform(message -> {
					LocalDateTime timestamp = LocalDateTime.now();

					return new DHTReading(
							new DailyKey(
									message.getSensorId(),
									timestamp.toLocalDate(),
									timestamp.toLocalTime().toNanoOfDay()
							),
							message.getTemperature(),
							message.getHumidity()
					);
				})
				.log()
				.toReactivePublisher();
	}

	@Bean
	public MessageChannel dhtReadingsFlowInput() {
		return MessageChannels.flux().get();
	}
}
