package com.sensoric.readings.domain.repository;

import com.sensoric.readings.domain.model.DHTReading;
import org.springframework.data.cassandra.core.ReactiveCassandraOperations;

public class DHTReadingRepositoryImpl extends AbstractDailyReadingRepository<DHTReading> {
    public DHTReadingRepositoryImpl(ReactiveCassandraOperations operations) {
        super(operations);
    }

    @Override
    protected Class<DHTReading> getJavaType() {
        return DHTReading.class;
    }
}
