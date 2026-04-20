package com.pragma.ms_technology.domain.spi;

import com.pragma.ms_technology.domain.model.Technology;
import reactor.core.publisher.Mono;

public interface ITechnologyPersistencePort {
    Mono<Technology> save(Technology technology);

    Mono<Boolean> existsByName(String name);

    Mono<Technology> findById(Long id);
}
