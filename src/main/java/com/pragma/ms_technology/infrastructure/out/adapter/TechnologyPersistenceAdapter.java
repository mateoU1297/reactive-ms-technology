package com.pragma.ms_technology.infrastructure.out.adapter;

import com.pragma.ms_technology.domain.model.Technology;
import com.pragma.ms_technology.domain.spi.ITechnologyPersistencePort;
import com.pragma.ms_technology.infrastructure.out.mapper.ITechnologyEntityMapper;
import com.pragma.ms_technology.infrastructure.out.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class TechnologyPersistenceAdapter implements ITechnologyPersistencePort {

    private final TechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;

    @Override
    public Mono<Technology> save(Technology technology) {
        return technologyRepository.save(
                technologyEntityMapper.toEntity(technology)
        ).map(technologyEntityMapper::toDomain);
    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        return technologyRepository.existsByName(name);
    }

    @Override
    public Mono<Technology> findById(Long id) {
        return technologyRepository.findById(id)
                .map(technologyEntityMapper::toDomain);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return technologyRepository.deleteById(id);
    }

    @Override
    public Mono<Boolean> existsById(Long id) {
        return technologyRepository.existsById(id);
    }
}