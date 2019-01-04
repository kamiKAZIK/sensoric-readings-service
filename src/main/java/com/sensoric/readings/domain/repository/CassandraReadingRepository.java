package com.sensoric.readings.domain.repository;

import com.sensoric.readings.domain.model.Reading;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CassandraReadingRepository<K, T extends Reading<K>> extends ReactiveCassandraRepository<T, K>, ReadingRepository<K, T> {
}
