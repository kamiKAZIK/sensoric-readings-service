package com.sensoric.readings.config;

import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.stream.Collectors;

@EnableConfigurationProperties(value = SecurityProperties.class)
@EnableWebFluxSecurity
@Configuration
public class SecurityConfiguration {
	private static final String ROLE_USER = "USER";
	private static final String ROLE_ACTUATOR = "ACTUATOR";

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http.csrf()
				.disable()
				.authorizeExchange()
				.matchers(EndpointRequest.toAnyEndpoint())
				.hasRole(ROLE_ACTUATOR)
				.anyExchange()
				.hasRole(ROLE_USER)
				.and()
				.httpBasic();

		return http.build();
	}

	@Bean
	public ReactiveUserDetailsService userDetailsService(SecurityProperties properties) {
		return new MapReactiveUserDetailsService(
				properties.getUsers()
						.stream()
						.map(this::buildUserDetails)
						.collect(Collectors.toSet())
		);
	}

	private UserDetails buildUserDetails(SecurityProperties.UserProperties properties) {
		return User.withUsername(properties.getName())
				.password(properties.getPassword())
				.roles(properties.getRoles())
				.build();
    }
}
