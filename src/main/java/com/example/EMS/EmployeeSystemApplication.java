package com.example.EMS;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.EMS.entity.Employee;
import com.example.EMS.service.EmployeeService;

import java.util.List;

@SpringBootApplication
public class EmployeeSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(EmployeeService employeeService) {
        return args -> {
            System.out.println("=== DEMO START ===");

            // Example: Get Employee by ID
            try {
                Employee employee = employeeService.getEmployeeById(1L);
                System.out.println("Fetched Employee by ID: " + employee.getName() +
                        " (" + employee.getDepartment() + "), Salary: " + employee.getSalary());
            } catch (Exception e) {
                System.out.println("Employee with ID 1 not found.");
            }

            // Example: Get Employees by Department
            List<Employee> itEmployees = employeeService.getEmployeesByDepartment("IT");
            System.out.println("Employees in IT Department:");
            itEmployees.forEach(e ->
                    System.out.println(e.getId() + " - " + e.getName() + ", Salary: " + e.getSalary()));

            System.out.println("=== DEMO END ===");
        };
    }
}
