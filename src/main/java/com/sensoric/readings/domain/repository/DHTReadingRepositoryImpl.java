package com.sensoric.readings.domain.repository;

import com.sensoric.readings.domain.model.DHTReading;
import org.springframework.data.cassandra.core.ReactiveCassandraOperations;

class DHTReadingRepositoryImpl extends AbstractReadingRepository<DHTReading.DailyKey, DHTReading> {
    DHTReadingRepositoryImpl(ReactiveCassandraOperations operations) {
        super(operations);
    }

    @Override
    protected Class<DHTReading> getJavaType() {
        return DHTReading.class;
    }
}
