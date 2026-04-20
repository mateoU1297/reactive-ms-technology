package com.pragma.ms_technology.domain.validator;

import com.pragma.ms_technology.domain.exception.InvalidFieldException;
import com.pragma.ms_technology.domain.model.Technology;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class TechnologyValidatorTest {

    @Test
    void validate_validTechnology_success() {
        Technology technology = new Technology(null, "Java", "Programming language");

        StepVerifier.create(TechnologyValidator.validate(technology))
                .expectNextMatches(t -> t.getName().equals("Java"))
                .verifyComplete();
    }

    @Test
    void validate_nullName_throwsInvalidField() {
        Technology technology = new Technology(null, null, "Description");

        StepVerifier.create(TechnologyValidator.validate(technology))
                .expectErrorMatches(e -> e instanceof InvalidFieldException &&
                        e.getMessage().equals("Name is required"))
                .verify();
    }

    @Test
    void validate_blankName_throwsInvalidField() {
        Technology technology = new Technology(null, "   ", "Description");

        StepVerifier.create(TechnologyValidator.validate(technology))
                .expectErrorMatches(e -> e instanceof InvalidFieldException &&
                        e.getMessage().equals("Name is required"))
                .verify();
    }

    @Test
    void validate_nameTooLong_throwsInvalidField() {
        Technology technology = new Technology(null, "A".repeat(51), "Description");

        StepVerifier.create(TechnologyValidator.validate(technology))
                .expectErrorMatches(e -> e instanceof InvalidFieldException &&
                        e.getMessage().contains("50"))
                .verify();
    }

    @Test
    void validate_nameMaxLength_success() {
        Technology technology = new Technology(null, "A".repeat(50), "Description");

        StepVerifier.create(TechnologyValidator.validate(technology))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void validate_nullDescription_throwsInvalidField() {
        Technology technology = new Technology(null, "Java", null);

        StepVerifier.create(TechnologyValidator.validate(technology))
                .expectErrorMatches(e -> e instanceof InvalidFieldException &&
                        e.getMessage().equals("Description is required"))
                .verify();
    }

    @Test
    void validate_blankDescription_throwsInvalidField() {
        Technology technology = new Technology(null, "Java", "   ");

        StepVerifier.create(TechnologyValidator.validate(technology))
                .expectErrorMatches(e -> e instanceof InvalidFieldException &&
                        e.getMessage().equals("Description is required"))
                .verify();
    }

    @Test
    void validate_descriptionTooLong_throwsInvalidField() {
        Technology technology = new Technology(null, "Java", "A".repeat(91));

        StepVerifier.create(TechnologyValidator.validate(technology))
                .expectErrorMatches(e -> e instanceof InvalidFieldException &&
                        e.getMessage().contains("90"))
                .verify();
    }

    @Test
    void validate_descriptionMaxLength_success() {
        Technology technology = new Technology(null, "Java", "A".repeat(90));

        StepVerifier.create(TechnologyValidator.validate(technology))
                .expectNextCount(1)
                .verifyComplete();
    }
}
