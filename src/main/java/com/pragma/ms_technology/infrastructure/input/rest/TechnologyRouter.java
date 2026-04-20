package com.pragma.ms_technology.infrastructure.input.rest;

import com.pragma.ms_technology.application.dto.TechnologyRequest;
import com.pragma.ms_technology.application.dto.TechnologyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TechnologyRouter {

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/technologies",
                    method = RequestMethod.POST,
                    beanClass = TechnologyRestHandler.class,
                    beanMethod = "save",
                    operation = @Operation(
                            operationId = "saveTechnology",
                            summary = "Register a new technology",
                            tags = {"Technology"},
                            requestBody = @RequestBody(
                                    required = true,
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = TechnologyRequest.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "201",
                                            description = "Technology created successfully",
                                            content = @Content(schema = @Schema(implementation = TechnologyResponse.class))
                                    ),
                                    @ApiResponse(
                                            responseCode = "400",
                                            description = "Invalid field"
                                    ),
                                    @ApiResponse(
                                            responseCode = "409",
                                            description = "Technology already exists"
                                    )
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> technologyRoutes(TechnologyRestHandler handler) {
        return RouterFunctions.route()
                .POST("/api/v1/technologies", handler::save)
                .build();
    }
}