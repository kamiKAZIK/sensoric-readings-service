package com.sensoric.readings.domain.command;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class FetchLastReadingCommand {
	private final UUID sensorId;

	private final LocalDate dateFrom, dateTo;
}
