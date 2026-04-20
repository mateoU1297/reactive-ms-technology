package com.pragma.ms_technology.domain.validator;

import com.pragma.ms_technology.domain.exception.InvalidFieldException;
import com.pragma.ms_technology.domain.model.Technology;
import reactor.core.publisher.Mono;

public class TechnologyValidator {

    private static final int MAX_NAME_LENGTH = 50;
    private static final int MAX_DESCRIPTION_LENGTH = 90;

    private TechnologyValidator() {}

    public static Mono<Technology> validate(Technology technology) {
        return validateName(technology.getName())
                .then(validateDescription(technology.getDescription()))
                .thenReturn(technology);
    }

    private static Mono<Void> validateName(String name) {
        if (name == null || name.isBlank())
            return Mono.error(new InvalidFieldException("Name is required"));

        if (name.length() > MAX_NAME_LENGTH)
            return Mono.error(new InvalidFieldException(
                    "Name must not exceed " + MAX_NAME_LENGTH + " characters"
            ));

        return Mono.empty();
    }

    private static Mono<Void> validateDescription(String description) {
        if (description == null || description.isBlank())
            return Mono.error(new InvalidFieldException("Description is required"));

        if (description.length() > MAX_DESCRIPTION_LENGTH)
            return Mono.error(new InvalidFieldException(
                    "Description must not exceed " + MAX_DESCRIPTION_LENGTH + " characters"
            ));

        return Mono.empty();
    }
}
