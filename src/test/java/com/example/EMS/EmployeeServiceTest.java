package com.example.EMS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.EMS.entity.Employee;
import com.example.EMS.repository.EmployeeRepository;
import com.example.EMS.service.EmployeeServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee(1L, "John Doe", "IT", 50000);
    }

    @Test
    void createEmployee_validInput_success() {
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee saved = employeeService.createEmployee(employee);

        assertNotNull(saved);
        assertEquals("John Doe", saved.getName());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void createEmployee_nullName_throwsException() {
        employee.setName(null);

        assertThrows(IllegalArgumentException.class, () -> employeeService.createEmployee(employee));
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void getEmployeeById_validId_success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee found = employeeService.getEmployeeById(1L);

        assertNotNull(found);
        assertEquals(1L, found.getId());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void getEmployeeById_invalidId_throwsException() {
        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> employeeService.getEmployeeById(2L));
    }

    @Test
    void updateEmployee_validId_success() {
        Employee updated = new Employee(1L, "Jane Doe", "HR", 60000);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updated);

        Employee result = employeeService.updateEmployee(1L, updated);

        assertEquals("Jane Doe", result.getName());
        assertEquals("HR", result.getDepartment());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void updateEmployee_invalidId_throwsException() {
        Employee updated = new Employee(2L, "Jane Doe", "HR", 60000);
        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> employeeService.updateEmployee(2L, updated));
    }

    @Test
    void deleteEmployee_validId_success() {
        when(employeeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteEmployee_invalidId_throwsException() {
        when(employeeRepository.existsById(2L)).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> employeeService.deleteEmployee(2L));
        verify(employeeRepository, never()).deleteById(anyLong());
    }
}
