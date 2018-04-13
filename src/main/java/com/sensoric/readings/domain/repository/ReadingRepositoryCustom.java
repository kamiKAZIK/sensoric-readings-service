package com.sensoric.readings.domain.repository;

import com.mongodb.client.result.UpdateResult;
import com.sensoric.readings.domain.model.Reading;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface ReadingRepositoryCustom<V, T extends Reading<V>> {
	Mono<T> findReadingsBetweenDates(String sensorId, LocalDateTime timestampFrom, LocalDateTime timestampTo, Class<T> cls);
	Mono<UpdateResult> addReadingValue(String sensorId, V value, LocalDateTime timestamp,  Class<V> cls);
}
