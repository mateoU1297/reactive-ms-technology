package com.pragma.ms_technology.application.handler;

import com.pragma.ms_technology.application.dto.TechnologyRequest;
import com.pragma.ms_technology.application.dto.TechnologyResponse;
import reactor.core.publisher.Mono;

public interface ITechnologyHandler {
    Mono<TechnologyResponse> save(TechnologyRequest request);
}
