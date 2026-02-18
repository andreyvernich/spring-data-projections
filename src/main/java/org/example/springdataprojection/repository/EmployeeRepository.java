package org.example.springdataprojection.repository;

import org.example.springdataprojection.entity.Employee;
import org.example.springdataprojection.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e.firstName as firstName, e.lastName as lastName, " +
            "e.position as position, d.name as departmentName " +
            "FROM Employee e JOIN e.department d")
    List<EmployeeProjection> findAllProjectedBy();

    @Query("SELECT e.firstName as firstName, e.lastName as lastName, " +
            "e.position as position, d.name as departmentName " +
            "FROM Employee e JOIN e.department d WHERE e.id = :id")
    Optional<EmployeeProjection> findProjectedById(Long id);

    List<EmployeeProjection> findByDepartmentId(Long departmentId);
}


