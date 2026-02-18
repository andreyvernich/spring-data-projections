package org.example.springdataprojection.service;

import org.example.springdataprojection.dto.EmployeeDto;

import org.example.springdataprojection.projection.EmployeeProjection;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto dto);

    Optional<EmployeeDto> getEmployeeDtoById(Long id);

    Optional<EmployeeProjection> getEmployeeProjectionById(Long id);

    List<EmployeeDto> getAllEmployeeDto();

    List<EmployeeProjection> getAllEmployeeProjections();

    EmployeeDto updateEmployee(Long id, EmployeeDto dto);

    void deleteEmployee(Long id);

    List<EmployeeProjection> getEmployeesByDepartment(Long departmentId);

}
