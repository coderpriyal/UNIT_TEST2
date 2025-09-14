package com.example.EMS.repository;
import com.example.EMS.entity.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
     List<Employee> findByDepartment(String department);
}
