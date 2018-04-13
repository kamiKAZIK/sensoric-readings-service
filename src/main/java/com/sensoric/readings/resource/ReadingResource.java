package com.sensoric.readings.resource;

import com.sensoric.readings.domain.model.TemperatureAndHumidityReading;
import com.sensoric.readings.domain.model.TemperatureAndHumidityReading.ReadingValue;
import com.sensoric.readings.domain.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
public class ReadingResource {
	private final ReadingService service;

	@Autowired
	public ReadingResource(ReadingService service) {
        this.service = service;
    }

    @GetMapping(value = "test")
	public Mono<TemperatureAndHumidityReading> test() {
		return service.persistTemperatureAndHumidity("12345-678-910", new ReadingValue(25.0f, 60.0f), LocalDateTime.now());
	}
}
