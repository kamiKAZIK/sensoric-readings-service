package com.sensoric.readings.domain.model;

import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public abstract class DailyReading {
    @PrimaryKey
    private final DailyKey key;

    @Data
    @PrimaryKeyClass
    public static final class DailyKey {
        @PrimaryKeyColumn(name = "sensor_id", type = PrimaryKeyType.PARTITIONED)
        private final UUID sensorId;

        @PrimaryKeyColumn(name = "write_date", type = PrimaryKeyType.PARTITIONED)
        private final LocalDate writeDate;

        @PrimaryKeyColumn(value = "write_time", type = PrimaryKeyType.CLUSTERED)
        private final LocalTime writeTime;
    }
}
