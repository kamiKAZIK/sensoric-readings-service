package com.sensoric.readings.domain.service;

import com.sensoric.readings.domain.command.FetchLastReadingCommand;
import com.sensoric.readings.domain.command.FetchReadingsCommand;
import com.sensoric.readings.domain.command.RegisterDHTReadingCommand;
import com.sensoric.readings.domain.dto.FetchDHTReadingsDTO;
import com.sensoric.readings.domain.dto.FetchLastDHTReadingDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DHTReadingService {
    Flux<FetchDHTReadingsDTO> fetchReadings(FetchReadingsCommand command);
    Mono<FetchLastDHTReadingDTO> fetchLastReading(FetchLastReadingCommand command);
    Mono<RegisterDHTReadingCommand> registerReading(RegisterDHTReadingCommand command);
}
