package com.sensoric.readings.domain.repository;

import com.sensoric.readings.domain.model.Reading;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import java.util.UUID;

public interface ReadingRepository extends ReactiveCassandraRepository<Reading, UUID> {
}
