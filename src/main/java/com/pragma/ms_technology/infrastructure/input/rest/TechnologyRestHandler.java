package com.pragma.ms_technology.infrastructure.input.rest;

import com.pragma.ms_technology.application.dto.TechnologyRequest;
import com.pragma.ms_technology.application.handler.ITechnologyHandler;
import com.pragma.ms_technology.domain.exception.InvalidFieldException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TechnologyRestHandler {

    private final ITechnologyHandler technologyHandler;
    private final Validator validator;

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(TechnologyRequest.class)
                .flatMap(req -> {
                    Set<ConstraintViolation<TechnologyRequest>> violations = validator.validate(req);
                    if (!violations.isEmpty()) {
                        String message = violations.stream()
                                .map(ConstraintViolation::getMessage)
                                .collect(Collectors.joining(", "));
                        return Mono.error(new InvalidFieldException(message));
                    }
                    return technologyHandler.save(req);
                })
                .flatMap(response -> ServerResponse.status(HttpStatus.CREATED).bodyValue(response));
    }
}
