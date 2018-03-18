package com.sensoric.readings.domain.model;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "readings")
@TypeAlias(value = "temperature")
public class TemperatureReading extends Reading<Float> {
}
