package com.sensoric.readings.domain.repository;

import org.springframework.data.cassandra.core.ReactiveCassandraOperations;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractDailyReadingRepository<T> implements DailyReadingRepository<T> {
    private final ReactiveCassandraOperations operations;

    protected AbstractDailyReadingRepository(ReactiveCassandraOperations operations) {
        this.operations = operations;
    }

    @Override
    public Flux<T> findReadings(UUID sensorId, LocalDateTime timestampFrom, LocalDateTime timestampTo) {
        return operations.select(
                Query.query(Criteria.where("sensor_id").is(sensorId))
                        .and(Criteria.where("write_date").in(listDates(timestampFrom, timestampTo)))
                        .and(Criteria.where("write_time").gte(timestampFrom.toLocalTime()))
                        .and(Criteria.where("write_time").lte(timestampTo.toLocalTime())),
                getJavaType()
        );
    }

    private List<LocalDateTime> listDates(LocalDateTime timestampFrom, LocalDateTime timestampTo) {
        return IntStream.rangeClosed(0, Long.valueOf(timestampFrom.until(timestampTo, ChronoUnit.DAYS)).intValue())
                .mapToObj(day -> timestampFrom.plusDays(day))
                .collect(Collectors.toList());
    }

    protected abstract Class<T> getJavaType();
}
