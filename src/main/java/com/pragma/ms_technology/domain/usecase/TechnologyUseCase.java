package com.pragma.ms_technology.domain.usecase;

import com.pragma.ms_technology.domain.api.ITechnologyServicePort;
import com.pragma.ms_technology.domain.exception.TechnologyAlreadyExistsException;
import com.pragma.ms_technology.domain.exception.TechnologyNotFoundException;
import com.pragma.ms_technology.domain.model.Technology;
import com.pragma.ms_technology.domain.spi.ITechnologyPersistencePort;
import com.pragma.ms_technology.domain.validator.TechnologyValidator;
import reactor.core.publisher.Mono;

public class TechnologyUseCase implements ITechnologyServicePort {

    private final ITechnologyPersistencePort technologyPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort) {
        this.technologyPersistencePort = technologyPersistencePort;
    }

    @Override
    public Mono<Technology> save(Technology technology) {
        return TechnologyValidator.validate(technology)
                .flatMap(t -> technologyPersistencePort.existsByName(t.getName()))
                .flatMap(exists -> {
                    if (exists)
                        return Mono.error(new TechnologyAlreadyExistsException(technology.getName()));
                    return technologyPersistencePort.save(technology);
                });
    }

    @Override
    public Mono<Technology> findById(Long id) {
        return technologyPersistencePort.findById(id)
                .switchIfEmpty(Mono.error(new TechnologyNotFoundException(id)));
    }

    @Override
    public Mono<Void> delete(Long id) {
        return technologyPersistencePort.existsById(id)
                .flatMap(exists -> {
                    if (!exists)
                        return Mono.error(new TechnologyNotFoundException(id));
                    return technologyPersistencePort.delete(id);
                });
    }
}