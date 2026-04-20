package com.pragma.ms_technology.infrastructure.out.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("technology")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyEntity {

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("description")
    private String description;
}