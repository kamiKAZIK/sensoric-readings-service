package com.sensoric.readings.config;

import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.async.client.MongoClientSettings;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@EnableConfigurationProperties(value = MongoProperties.class)
@EnableReactiveMongoRepositories(basePackages = "com.sensoric.readings.domain.repository")
@Configuration
public class MongoConfiguration extends AbstractReactiveMongoConfiguration {
	private final MongoProperties mongoProperties;
	private final String applicationName;

	@Autowired
	public MongoConfiguration(
			MongoProperties mongoProperties,
			@Value("${spring.application.name}") String applicationName
	) {
		this.mongoProperties = mongoProperties;
		this.applicationName = applicationName;
	}

	@Override
	protected String getDatabaseName() {
		return mongoProperties.getDatabase();
	}

	@Override
	public MongoClient reactiveMongoClient() {
		return MongoClients.create(buildSettings());
	}

	private MongoClientSettings buildSettings() {
		List<ServerAddress> hosts = Arrays.stream(mongoProperties.getHosts())
				.map(ServerAddress::new)
				.collect(Collectors.toList());

		ClusterSettings clusterSettings = ClusterSettings.builder().hosts(hosts).build();

		return MongoClientSettings.builder()
				.clusterSettings(clusterSettings)
				.credential(MongoCredential.createCredential(mongoProperties.getUser(), mongoProperties.getDatabase(), mongoProperties.getPassword().toCharArray()))
				.applicationName(applicationName)
				.build();
	}
}
