package com.sensoric.readings.domain.repository;

import com.mongodb.client.result.UpdateResult;
import com.sensoric.readings.domain.model.Reading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

//@Repository
public class ReadingRepositoryImpl<V, T extends Reading<V>> implements ReadingRepositoryCustom<V, T> {
	private final ReactiveMongoOperations operations;

	@Autowired
	public ReadingRepositoryImpl(ReactiveMongoOperations operations) {
		this.operations = operations;
	}

	@Override
	public Mono<T> findReadingsBetweenDates(String sensorId, LocalDateTime timestampFrom, LocalDateTime timestampTo, Class<T> cls) {
		return operations.findOne(
				Query.query(
						Criteria.where("sensorId")
								.is(sensorId)
								.and("days.day")
								.gte(timestampFrom.toLocalDate())
								.lte(timestampTo.toLocalDate())
								.and("days.hours.hour")
								.gte(timestampFrom.getHour())
								.lte(timestampTo.getHour())
								.and("days.hours.minutes.minute")
								.gte(timestampFrom.getMinute())
								.lte(timestampTo.getMinute())
								.and("days.hours.minutes.seconds.second")
								.gte(timestampFrom.getSecond())
								.lte(timestampTo.getSecond())
				),
				cls
		);
	}

	@Override
	public Mono<UpdateResult> addReadingValue(String sensorId, V value, LocalDateTime timestamp,  Class<V> cls) {
		/*return operations.updateFirst(
				Query.query(
						Criteria.where("sensorId")
								.is(sensorId)
				),
				cls
		);*/
		/*return operations.upsert(
				Query.query(
						Criteria.where("sensorId")
								.is(sensorId)
				),
				new Update()
						.setOnInsert("sensorId", sensorId)
						.addToSet("days", new Reading.Day<V>(timestamp.toLocalDate()))
						.addToSet("days.hours", new Reading.Day.Hour<V>(timestamp.getHour()))
						.addToSet("days.hours.minutes", new Reading.Day.Hour.Minute<V>(timestamp.getMinute()))
						.addToSet("days.hours.minutes.seconds", new Reading.Day.Hour.Minute.Second<V>(timestamp.getSecond(), value)),
				cls
		);*/
		return null;
	}
}
