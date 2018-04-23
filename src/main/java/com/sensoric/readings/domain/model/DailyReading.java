package com.sensoric.readings.domain.model;

import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public abstract class DailyReading<V> implements Reading<DailyReading.DailyKey, V> {
    @Data
    @PrimaryKeyClass
    public static final class DailyKey {
        @PrimaryKeyColumn(name = "sensor_id", type = PrimaryKeyType.PARTITIONED)
        private UUID sensorId;

        @PrimaryKeyColumn(name = "write_date", type = PrimaryKeyType.PARTITIONED)
        private LocalDate writeDate;

        @PrimaryKeyColumn(value = "write_time", type = PrimaryKeyType.CLUSTERED)
        private LocalTime writeTime;
    }
}
