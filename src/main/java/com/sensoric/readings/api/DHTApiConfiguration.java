package com.sensoric.readings.api;

import com.sensoric.readings.domain.command.FetchReadingsCommand;
import com.sensoric.readings.domain.model.DHTReading;
import com.sensoric.readings.domain.service.DHTReadingService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.UUID;

@Configuration
class DHTApiConfiguration {
	@RestController
	@RequestMapping(path = "/dht")
	static class DHTController {
		private final DHTReadingService dhtReadingService;

		DHTController(DHTReadingService dhtReadingService) {
			this.dhtReadingService = dhtReadingService;
		}

		@GetMapping(path = "/readings")
		public Flux<DHTReading> fetchReadings(
				@RequestParam UUID sensorId,
				@RequestParam LocalDateTime timestampFrom,
				@RequestParam LocalDateTime timestampTo
		) {
			return dhtReadingService.fetchReadings(new FetchReadingsCommand(sensorId, timestampFrom, timestampTo));
		}
	}
}
