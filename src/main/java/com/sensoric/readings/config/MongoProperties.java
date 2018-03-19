package com.sensoric.readings.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sensoric.mongo")
@Data
public class MongoProperties {
	private String[] hosts;
	private String database;
	private String user;
	private String password;
}
