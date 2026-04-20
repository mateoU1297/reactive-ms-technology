package com.pragma.ms_technology.infrastructure.config;

import com.pragma.ms_technology.domain.api.ITechnologyServicePort;
import com.pragma.ms_technology.domain.spi.ITechnologyPersistencePort;
import com.pragma.ms_technology.domain.usecase.TechnologyUseCase;
import com.pragma.ms_technology.infrastructure.out.adapter.TechnologyPersistenceAdapter;
import com.pragma.ms_technology.infrastructure.out.mapper.ITechnologyEntityMapper;
import com.pragma.ms_technology.infrastructure.out.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final TechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;

    @Bean
    public ITechnologyPersistencePort technologyPersistencePort() {
        return new TechnologyPersistenceAdapter(technologyRepository, technologyEntityMapper);
    }

    @Bean
    public ITechnologyServicePort technologyServicePort() {
        return new TechnologyUseCase(technologyPersistencePort());
    }
}