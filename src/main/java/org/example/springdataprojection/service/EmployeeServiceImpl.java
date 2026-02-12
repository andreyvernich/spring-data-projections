package org.example.springdataprojection.service;

import lombok.AllArgsConstructor;
import org.example.springdataprojection.dto.EmployeeDto;
import org.example.springdataprojection.entity.Department;
import org.example.springdataprojection.entity.Employee;
import org.example.springdataprojection.mapper.EmployeeMapper;
import org.example.springdataprojection.projection.EmployeeProjection;
import org.example.springdataprojection.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;
    private final EmployeeMapper employeeMapper;

    @Override
    @Transactional
    public EmployeeDto createEmployee(EmployeeDto dto) {
        Department department = departmentService.getDepartmentById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Employee employee = employeeMapper.toEntity(dto, department);
        Employee saved = employeeRepository.save(employee);
        return employeeMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeDto> getEmployeeDtoById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeProjection> getEmployeeProjectionById(Long id) {
        return employeeRepository.findProjectedById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployeeDto() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeProjection> getAllEmployeeProjections() {
        return employeeRepository.findAllProjectedBy();
    }

    @Override
    @Transactional
    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        Employee updated = employeeRepository.save(employee);
        return employeeMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeProjection> getEmployeesByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }
}
