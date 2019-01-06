package com.sensoric.readings.domain.repository;

import com.sensoric.readings.domain.model.DHTReading;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/dht-readings", collectionResourceRel = "dht-readings")
public interface DHTReadingRepository extends CassandraRepository<DHTReading, DHTReading.DailyKey> {
}
