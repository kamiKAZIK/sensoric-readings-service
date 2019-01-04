package com.sensoric.readings.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RegisterDHTReadingDTO {
	private final UUID sensorId;

	private final LocalDateTime timestamp;

	private final Float temperature, humidity;
}
