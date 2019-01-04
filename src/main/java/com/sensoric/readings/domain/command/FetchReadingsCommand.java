package com.sensoric.readings.domain.command;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class FetchReadingsCommand {
	private final UUID sensorId;

	private final LocalDateTime timestampFrom, timestampTo;
}
