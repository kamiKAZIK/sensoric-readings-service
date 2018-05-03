package com.sensoric.readings.domain.repository;

import com.sensoric.readings.domain.model.DailyReading.DailyKey;
import com.sensoric.readings.domain.model.Reading;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CassandraReadingRepository<T extends Reading> extends ReactiveCassandraRepository<T, DailyKey>, ReadingRepository<T> {
}
