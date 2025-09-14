Employee Management System – Service Layer with Tests

A simple Spring Boot project demonstrating service layer development and testing with JUnit 5 + Mockito.
The app manages Employee entities with basic CRUD operations.

📂 Project Structure
com.example.employeesystem
├── EmployeeSystemApplication.java   # Main Spring Boot app
├── entity/Employee.java              # Employee JPA entity
├── repository/EmployeeRepository.java# JPA repository
├── service/EmployeeService.java      # Service interface
├── service/impl/EmployeeServiceImpl  # Service implementation
└── service/EmployeeServiceTest.java  # Unit tests (JUnit + Mockito)

⚙️ Features

Entity: Employee (id, name, department, salary)

Repository: EmployeeRepository extends JpaRepository

Service Layer: CRUD methods with business rules:

Name cannot be null/blank

Salary must be > 0

NoSuchElementException for invalid IDs

Testing: Unit tests using JUnit 5 + Mockito (mocked repository)

Demo: A CommandLineRunner runs sample CRUD operations on startup
