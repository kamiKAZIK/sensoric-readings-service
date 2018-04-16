package com.sensoric.readings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

@EnableReactiveCassandraRepositories
@SpringBootApplication
public class ReadingsService {
	public static void main(String[] args) {
		SpringApplication.run(ReadingsService.class, args);
	}

	@Configuration
	@EnableCassandraRepositories
	static class CassandraConfig extends AbstractReactiveCassandraConfiguration {
		@Override
		protected String getKeyspaceName() {
			return "sensoric";
		}
	}
}
