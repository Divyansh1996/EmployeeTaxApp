package com.example.employeetax.repository;

import com.example.employeetax.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query(value = "SELECT * FROM employee u WHERE u.employee_id = :employeeId",
            nativeQuery = true)
    Employee findEmployeeById(
            @Param("employeeId") Integer employeeId);
}
