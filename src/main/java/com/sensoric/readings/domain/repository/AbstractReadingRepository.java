package com.sensoric.readings.domain.repository;

import com.sensoric.readings.domain.model.Reading;
import org.springframework.data.cassandra.core.ReactiveCassandraOperations;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractReadingRepository<T extends Reading> implements ReadingRepository<T> {
    private final ReactiveCassandraOperations operations;

    protected AbstractReadingRepository(ReactiveCassandraOperations operations) {
        this.operations = operations;
    }

    @Override
    public Flux<T> findReadings(UUID sensorId, LocalDateTime timestampFrom, LocalDateTime timestampTo) {
        return operations.select(
                Query.query(Criteria.where("sensor_id").is(sensorId))
                        .and(Criteria.where("write_date").in(listDates(timestampFrom.toLocalDate(), timestampTo.toLocalDate())))
                        .and(Criteria.where("write_time").gte(timestampFrom.toLocalTime()))
                        .and(Criteria.where("write_time").lte(timestampTo.toLocalTime())),
                getJavaType()
        );
    }

    @Override
    public Mono<T> findLastReading(UUID sensorId, LocalDate dateFrom, LocalDate dateTo) {
        return operations.selectOne(
                Query.query(Criteria.where("sensor_id").is(sensorId))
                        .and(Criteria.where("write_date").in(listDates(dateFrom, dateTo)))
                        .limit(1),
                getJavaType()
        );
    }

    private List<LocalDate> listDates(LocalDate dateFrom, LocalDate dateTo) {
        return IntStream.rangeClosed(0, Long.valueOf(dateFrom.until(dateTo, ChronoUnit.DAYS)).intValue())
                .mapToObj(day -> dateFrom.plusDays(day))
                .collect(Collectors.toList());
    }

    protected abstract Class<T> getJavaType();
}
