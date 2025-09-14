🏢 Employee Management System (EMS)

A simple Spring Boot project that manages employees with basic CRUD-style APIs.
Includes JUnit + Mockito tests for 100% coverage.

🚀 Features

Get employee by ID

Get employees by Department

Each employee has:

id (Long)

name (String)

department (String)

salary (Double)

📌 Technologies

Java 21

Spring Boot 3.x

Spring Data JPA

PostgreSQL (or H2 for tests)

JUnit 5 + Mockito

🔥 API Endpoints
Method	Endpoint	Description
GET	/employees/{id}	Get employee by ID
GET	/employees/department/{dept}	Get employees by department
🧪 Testing

Unit tests with Mockito for service layer

Controller tests with MockMvc

100% test coverage (verified with JaCoCo)
