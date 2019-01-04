package com.sensoric.readings.api.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FetchDHTReadingsResponse {
	private final LocalDateTime timestamp;

	private final Float temperature, humidity;
}
