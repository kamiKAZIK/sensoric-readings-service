package com.sensoric.readings.domain.command;

import lombok.Data;

import java.util.UUID;

@Data
public class RegisterDHTReadingCommand {
	private final UUID sensorId;
	private final Float temperature, humidity;
}
