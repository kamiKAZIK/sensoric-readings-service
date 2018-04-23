package com.sensoric.readings.domain.model;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Table;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(value = "dht_readings")
public class DHTReading extends Reading {
    private Float temperature;
}
