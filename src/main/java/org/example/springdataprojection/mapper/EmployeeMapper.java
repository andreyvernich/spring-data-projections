package org.example.springdataprojection.mapper;

import org.example.springdataprojection.dto.EmployeeDto;
import org.example.springdataprojection.entity.Department;
import org.example.springdataprojection.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = DepartmentMapper.class)
public interface EmployeeMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", source = "department")
    Employee toEntity(EmployeeDto dto, Department department);

    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "departmentName", source = "department.name")
    EmployeeDto toDto(Employee employee);
}
