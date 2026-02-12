package org.example.springdataprojection.mapper;

import org.example.springdataprojection.dto.DepartmentDto;
import org.example.springdataprojection.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employees", ignore = true)
    Department toEntity(DepartmentDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employees", ignore = true)
    void updateEntity(DepartmentDto dto, @MappingTarget Department department);

    DepartmentDto toDto(Department department);
}
