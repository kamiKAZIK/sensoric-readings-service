package com.sensoric.readings.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public abstract class Reading<T> {
	@Id
	private String id;
	private String sensorId;
	//private Set<Day<T>> days = new HashSet<>();
	private Map<String, Map<Integer, Map<Integer, Map<Integer, T>>>> entries = new HashMap<>();

	public Reading() {
	}

	public Reading(String sensorId) {
		this.sensorId = sensorId;
	}
	/*@Data
	public static final class Day<T> {
		private LocalDate day;
		private Set<Hour<T>> hours = new HashSet<>();

		public Day(LocalDate day) {
			this.day = day;
		}

		@Data
		public static final class Hour<T> {
			private Integer hour;
			private Set<Minute<T>> minutes = new HashSet<>();

			public Hour(Integer hour) {
				this.hour = hour;
			}

			@Data
			public static final class Minute<T> {
				private Integer minute;
				private Set<Second<T>> seconds = new HashSet<>();

				public Minute(Integer minute) {
					this.minute = minute;
				}

				@Data
				public static final class Second<T> {
					private Integer second;
					private T value;

					public Second(Integer second, T value) {
						this.second = second;
						this.value = value;
					}
				}
			}
		}
	}*/
}
