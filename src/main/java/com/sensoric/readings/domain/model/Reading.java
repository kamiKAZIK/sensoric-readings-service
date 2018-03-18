package com.sensoric.readings.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@Document(collection = "readings")
public abstract class Reading<T> {
    @Id
    private String id;
    private String sensorId;
    private Map<LocalDate, Map<Integer, Map<Integer, Map<Integer, T>>>> entries = new HashMap<>();
}
