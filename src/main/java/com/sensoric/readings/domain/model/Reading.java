package com.sensoric.readings.domain.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(value = "readings")
@Data
public class Reading {
	@PrimaryKey
	private UUID sensorId;
	private LocalDateTime timestamp;
	private String value;
}
