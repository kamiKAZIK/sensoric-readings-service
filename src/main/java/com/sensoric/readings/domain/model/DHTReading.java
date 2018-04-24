package com.sensoric.readings.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.Table;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(value = "dht_readings")
public final class DHTReading extends DailyReading {
    private final Float temperature, humidity;

    public DHTReading(DailyReading.DailyKey key, Float temperature, Float humidity) {
        super(key);
        this.temperature = temperature;
        this.humidity = humidity;
    }
}
