package com.example.EMS.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.EMS.entity.Employee;
import com.example.EMS.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = service.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/department/{dept}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable String dept) {
        List<Employee> employees = service.getEmployeesByDepartment(dept);
        return ResponseEntity.ok(employees);
    }
}