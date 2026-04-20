package com.pragma.ms_technology.domain.api;

import com.pragma.ms_technology.domain.model.Technology;
import reactor.core.publisher.Mono;

public interface ITechnologyServicePort {
    Mono<Technology> save(Technology technology);

    Mono<Technology> findById(Long id);
}