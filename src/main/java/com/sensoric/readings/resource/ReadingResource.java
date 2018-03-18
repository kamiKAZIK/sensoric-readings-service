package com.sensoric.readings.resource;

import com.sensoric.readings.domain.model.Reading;
import com.sensoric.readings.domain.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class ReadingResource {
    private final ReadingService service;

    @Autowired
    public ReadingResource(ReadingService service) {
        this.service = service;
    }

    @GetMapping("/resources/{sensor}")
    public Flux<Reading> getAllTweets(@PathVariable UUID sensor, @RequestParam Long timestampFrom) {
        return null;
    }

    @GetMapping("/resources")
    public void test() {
        String uuid = "f01543c7-2831-4909-bf72-cc5f498b951e";
        service.persistTemperature(
                uuid,
                Double.valueOf(Math.abs(Math.random() * 10)).floatValue(),
                LocalDateTime.now()
        );
    }
}
