package com.sensoric.readings.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FetchDHTReadingsDTO {
	private final LocalDateTime timestamp;

	private final Float temperature, humidity;
}
