package com.example.EMS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.EMS.entity.Employee;
import com.example.EMS.repository.EmployeeRepository;
import com.example.EMS.service.EmployeeService;
import com.example.EMS.service.EmployeeServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    private Employee emp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        emp = new Employee(1L, "Alice", "HR", 50000.0);
    }

    @Test
    void testGetEmployeeById_Found() {
        when(repository.findById(1L)).thenReturn(Optional.of(emp));

        Employee result = service.getEmployeeById(1L);

        assertNotNull(result);
        assertEquals("Alice", result.getName());
        assertEquals(50000.0, result.getSalary());
    }

    @Test
    void testGetEmployeeById_NotFound() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.getEmployeeById(2L));
    }

    @Test
    void testGetEmployeesByDepartment() {
        when(repository.findByDepartment("HR")).thenReturn(Arrays.asList(emp));

        List<Employee> result = service.getEmployeesByDepartment("HR");

        assertEquals(1, result.size());
        assertEquals("HR", result.get(0).getDepartment());
    }
}