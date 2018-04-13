package com.sensoric.readings.domain.repository;

import com.mongodb.client.result.UpdateResult;
import com.sensoric.readings.domain.model.TemperatureAndHumidityReading;
import com.sensoric.readings.domain.model.TemperatureAndHumidityReading.ReadingValue;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface TemperatureAndHumidityReadingRepository extends ReactiveMongoRepository<TemperatureAndHumidityReading, String> {
	Mono<TemperatureAndHumidityReading> findBySensorId(String sensorId);
//public interface TemperatureAndHumidityReadingRepository extends ReadingRepository<ReadingValue, TemperatureAndHumidityReading> {
	/*default Mono<TemperatureAndHumidityReading> findReadingsBetweenDates(String sensorId, LocalDateTime timestampFrom, LocalDateTime timestampTo) {
		return findReadingsBetweenDates(sensorId, timestampFrom, timestampTo, TemperatureAndHumidityReading.class);
	}

	default Mono<UpdateResult> addReadingValue(String sensorId, TemperatureAndHumidityReading.ReadingValue value, LocalDateTime timestamp) {
		return addReadingValue(sensorId, value, timestamp, TemperatureAndHumidityReading.ReadingValue.class);
	}*/
}
