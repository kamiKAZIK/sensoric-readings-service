package com.sensoric.readings.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "sensoric.security")
@Getter
@Setter
public class SecurityProperties {
	private UserProperties user, manager;

	public List<UserProperties> getUsers() {
		return Arrays.asList(user, manager);
	}

	@Getter
	@Setter
	public static final class UserProperties {
		private String name, password;
		private String[] roles;
	}
}
