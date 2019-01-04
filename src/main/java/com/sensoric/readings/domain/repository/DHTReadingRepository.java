package com.sensoric.readings.domain.repository;

import com.sensoric.readings.domain.model.DHTReading;

public interface DHTReadingRepository extends CassandraReadingRepository<DHTReading.DailyKey, DHTReading> {
}
