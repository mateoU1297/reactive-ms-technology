package com.pragma.ms_technology.domain.usecase;

import com.pragma.ms_technology.domain.exception.TechnologyAlreadyExistsException;
import com.pragma.ms_technology.domain.exception.TechnologyNotFoundException;
import com.pragma.ms_technology.domain.model.Technology;
import com.pragma.ms_technology.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TechnologyUseCaseTest {

    @Mock
    private ITechnologyPersistencePort technologyPersistencePort;

    @InjectMocks
    private TechnologyUseCase technologyUseCase;

    private Technology technology;

    @BeforeEach
    void setUp() {
        technology = new Technology(1L, "Java", "Programming language");
    }

    @Test
    void save_validTechnology_success() {
        when(technologyPersistencePort.existsByName("Java")).thenReturn(Mono.just(false));
        when(technologyPersistencePort.save(technology)).thenReturn(Mono.just(technology));

        StepVerifier.create(technologyUseCase.save(technology))
                .expectNextMatches(t -> t.getName().equals("Java"))
                .verifyComplete();
    }

    @Test
    void save_throwsAlreadyExists() {
        when(technologyPersistencePort.existsByName("Java")).thenReturn(Mono.just(true));

        StepVerifier.create(technologyUseCase.save(technology))
                .expectError(TechnologyAlreadyExistsException.class)
                .verify();
    }

    @Test
    void save_neverCallsSave() {
        when(technologyPersistencePort.existsByName("Java")).thenReturn(Mono.just(true));

        StepVerifier.create(technologyUseCase.save(technology))
                .expectError(TechnologyAlreadyExistsException.class)
                .verify();

        verify(technologyPersistencePort, never()).save(any());
    }

    @Test
    void findById_existingTechnology_success() {
        when(technologyPersistencePort.findById(1L)).thenReturn(Mono.just(technology));

        StepVerifier.create(technologyUseCase.findById(1L))
                .expectNextMatches(t -> t.getId().equals(1L))
                .verifyComplete();
    }

    @Test
    void findById_notFound_throwsTechnologyNotFound() {
        when(technologyPersistencePort.findById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(technologyUseCase.findById(1L))
                .expectError(TechnologyNotFoundException.class)
                .verify();
    }

    @Test
    void delete_existingTechnology_success() {
        when(technologyPersistencePort.existsById(1L)).thenReturn(Mono.just(true));
        when(technologyPersistencePort.delete(1L)).thenReturn(Mono.empty());

        StepVerifier.create(technologyUseCase.delete(1L))
                .verifyComplete();
    }

    @Test
    void delete_notFound_throwsTechnologyNotFound() {
        when(technologyPersistencePort.existsById(1L)).thenReturn(Mono.just(false));

        StepVerifier.create(technologyUseCase.delete(1L))
                .expectError(TechnologyNotFoundException.class)
                .verify();

        verify(technologyPersistencePort, never()).delete(any());
    }
}