package org.example.springdataprojection.service;

import org.example.springdataprojection.dto.DepartmentDto;
import org.example.springdataprojection.entity.Department;
import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    DepartmentDto createDepartment(DepartmentDto dto);

    Optional<Department> getDepartmentById(Long id);

    Optional<DepartmentDto> getDepartmentDtoById(Long id);

    List<DepartmentDto> getAllDepartmentDto();

    DepartmentDto updateDepartment(Long id, DepartmentDto dto);

    void deleteDepartment(Long id);

    Optional<Department> findDepartmentByName(String name);

}
