package com.pragma.ms_technology.domain.usecase;

import com.pragma.ms_technology.domain.api.ITechnologyServicePort;
import com.pragma.ms_technology.domain.exception.TechnologyAlreadyExistsException;
import com.pragma.ms_technology.domain.model.Technology;
import com.pragma.ms_technology.domain.spi.ITechnologyPersistencePort;
import reactor.core.publisher.Mono;

public class TechnologyUseCase implements ITechnologyServicePort {

    private final ITechnologyPersistencePort technologyPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort) {
        this.technologyPersistencePort = technologyPersistencePort;
    }

    @Override
    public Mono<Technology> save(Technology technology) {
        return technologyPersistencePort.existsByName(technology.getName())
                .flatMap(exists -> {
                    if (exists)
                        return Mono.error(new TechnologyAlreadyExistsException(technology.getName()));
                    return technologyPersistencePort.save(technology);
                });
    }
}