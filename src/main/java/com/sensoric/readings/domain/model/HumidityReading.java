package com.sensoric.readings.domain.model;

import org.springframework.data.annotation.TypeAlias;

@TypeAlias(value = "humidity")
public class HumidityReading extends Reading<Float> {
}
