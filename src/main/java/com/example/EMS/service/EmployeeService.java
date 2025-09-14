package com.example.EMS.service;

import java.util.List;

import com.example.EMS.entity.Employee;

public interface EmployeeService {
   Employee getEmployeeById(Long id);
    List<Employee> getEmployeesByDepartment(String department);
}
