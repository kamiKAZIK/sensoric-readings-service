package com.sensoric.readings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@EnableCassandraRepositories
@SpringBootApplication
public class ReadingsService {
	public static void main(String[] args) {
		SpringApplication.run(ReadingsService.class, args);
	}
}
