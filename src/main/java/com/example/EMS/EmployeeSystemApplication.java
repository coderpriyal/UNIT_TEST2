package com.example.EMS;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.EMS.entity.Employee;
import com.example.EMS.service.EmployeeService;

@SpringBootApplication
public class EmployeeSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(EmployeeService employeeService) {
        return args -> {
            System.out.println("=== DEMO START ===");

            // 1. Create
            Employee emp1 = new Employee(null, "Alice", "HR", 40000);
            Employee emp2 = new Employee(null, "Bob", "IT", 55000);

            emp1 = employeeService.createEmployee(emp1);
            emp2 = employeeService.createEmployee(emp2);

            System.out.println("Created: " + emp1.getId() + " " + emp1.getName());
            System.out.println("Created: " + emp2.getId() + " " + emp2.getName());

            // 2. Get All
            System.out.println("All Employees:");
            employeeService.getAllEmployees().forEach(e ->
                    System.out.println(e.getId() + " - " + e.getName() + " (" + e.getDepartment() + ")"));

            // 3. Get by ID
            Employee fetched = employeeService.getEmployeeById(emp1.getId());
            System.out.println("Fetched by ID: " + fetched.getName());

            // 4. Update
            emp1.setName("Alice Updated");
            emp1.setSalary(45000);
            Employee updated = employeeService.updateEmployee(emp1.getId(), emp1);
            System.out.println("Updated Employee: " + updated.getName() + " with salary " + updated.getSalary());

            // 5. Delete
            employeeService.deleteEmployee(emp2.getId());
            System.out.println("Deleted Employee with id " + emp2.getId());

            System.out.println("Remaining Employees:");
            employeeService.getAllEmployees().forEach(e ->
                    System.out.println(e.getId() + " - " + e.getName()));

            System.out.println("=== DEMO END ===");
        };
    }
}