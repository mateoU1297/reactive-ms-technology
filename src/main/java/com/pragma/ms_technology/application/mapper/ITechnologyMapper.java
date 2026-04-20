package com.pragma.ms_technology.application.mapper;

import com.pragma.ms_technology.application.dto.TechnologyRequest;
import com.pragma.ms_technology.application.dto.TechnologyResponse;
import com.pragma.ms_technology.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ITechnologyMapper {
    Technology toDomain(TechnologyRequest request);
    TechnologyResponse toResponse(Technology technology);
}