/*package com.sensoric.readings.config;

import lombok.Data;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Configuration;

@EnableBinding(Sink.class)
@Configuration
public class KafkaConfiguration {
	@StreamListener(Sink.INPUT)
	public void handle(MMM message) {
		System.out.println("Received: " + message);
	}

	@Data
	public static final class MMM {
		private String a;
	}
}*/
