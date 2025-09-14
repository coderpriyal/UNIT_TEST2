package com.example.EMS;
import com.example.EMS.controller.EmployeeController;
import com.example.EMS.entity.Employee;
import com.example.EMS.service.EmployeeService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    @Test
    void testGetEmployeeById_Found() throws Exception {
        Employee emp = new Employee(1L, "Alice", "HR", 50000.0);
        when(service.getEmployeeById(1L)).thenReturn(emp);

        mockMvc.perform(get("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.salary").value(50000.0));
    }

    @Test
    void testGetEmployeeById_NotFound() throws Exception {
        when(service.getEmployeeById(2L)).thenThrow(new NoSuchElementException());

        mockMvc.perform(get("/employees/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetEmployeesByDepartment() throws Exception {
        Employee emp1 = new Employee(1L, "Alice", "HR", 50000.0);
        Employee emp2 = new Employee(2L, "Bob", "HR", 60000.0);

        when(service.getEmployeesByDepartment("HR")).thenReturn(Arrays.asList(emp1, emp2));

        mockMvc.perform(get("/employees/department/HR")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].salary").value(60000.0));
    }
}