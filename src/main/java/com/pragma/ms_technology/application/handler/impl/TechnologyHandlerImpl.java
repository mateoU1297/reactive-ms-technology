package com.pragma.ms_technology.application.handler.impl;

import com.pragma.ms_technology.application.dto.TechnologyRequest;
import com.pragma.ms_technology.application.dto.TechnologyResponse;
import com.pragma.ms_technology.application.handler.ITechnologyHandler;
import com.pragma.ms_technology.application.mapper.ITechnologyMapper;
import com.pragma.ms_technology.domain.api.ITechnologyServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TechnologyHandlerImpl implements ITechnologyHandler {

    private final ITechnologyServicePort technologyServicePort;
    private final ITechnologyMapper technologyMapper;

    @Override
    public Mono<TechnologyResponse> save(TechnologyRequest request) {
        return technologyServicePort.save(technologyMapper.toDomain(request))
                .map(technologyMapper::toResponse);
    }

    @Override
    public Mono<TechnologyResponse> findById(Long id) {
        return technologyServicePort.findById(id)
                .map(technologyMapper::toResponse);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return technologyServicePort.delete(id);
    }
}
