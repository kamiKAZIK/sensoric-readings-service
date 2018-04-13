package com.sensoric.readings.domain.model;

import com.sensoric.readings.domain.model.TemperatureAndHumidityReading.ReadingValue;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TemperatureAndHumidityReading extends Reading<ReadingValue> {
	public TemperatureAndHumidityReading(String sensorId) {
		super(sensorId);
	}

	@Data
	public static final class ReadingValue {
		private final Float temperature;
		private final Float humidity;
	}
}
