package com.pragma.ms_technology.infrastructure.out.mapper;

import com.pragma.ms_technology.domain.model.Technology;
import com.pragma.ms_technology.infrastructure.out.entity.TechnologyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ITechnologyEntityMapper {
    TechnologyEntity toEntity(Technology technology);

    Technology toDomain(TechnologyEntity entity);
}
