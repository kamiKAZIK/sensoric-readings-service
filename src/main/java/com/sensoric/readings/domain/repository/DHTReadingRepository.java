package com.sensoric.readings.domain.repository;

import com.sensoric.readings.domain.model.DHTReading;
import com.sensoric.readings.domain.model.DailyReading;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import java.util.UUID;

public interface DHTReadingRepository extends CassandraReadingRepository<DHTReading> {
}
