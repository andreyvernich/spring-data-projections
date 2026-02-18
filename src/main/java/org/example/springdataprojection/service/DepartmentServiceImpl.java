package org.example.springdataprojection.service;

import lombok.AllArgsConstructor;
import org.example.springdataprojection.dto.DepartmentDto;
import org.example.springdataprojection.entity.Department;
import org.example.springdataprojection.mapper.DepartmentMapper;
import org.example.springdataprojection.repository.DepartmentRepository;
import org.example.springdataprojection.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    @Transactional
    public DepartmentDto createDepartment(DepartmentDto dto) {
        Department department = departmentMapper.toEntity(dto);
        Department saved = departmentRepository.save(department);
        return departmentMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DepartmentDto> getDepartmentDtoById(Long id) {
        return departmentRepository.findById(id)
                .map(departmentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDto> getAllDepartmentDto() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DepartmentDto updateDepartment(Long id, DepartmentDto dto) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        departmentMapper.updateEntity(dto, department);
        Department updated = departmentRepository.save(department);
        return departmentMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteDepartment(Long id) {
        if (!employeeRepository.findByDepartmentId(id).isEmpty()) {
            throw new RuntimeException("Cannot delete department with existing employees");
        }
        departmentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Department> findDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }
}
