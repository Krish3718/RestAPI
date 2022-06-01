package com.example.RestApi.controller;

import com.example.RestApi.model.Employee;
import com.example.RestApi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PublicController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp)
    {
        try {
            emp.setPassword(passwordEncoder.encode(emp.getPassword()));
            Employee employee = this.employeeService.createEmployeeService(emp);
            if (employee == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/addEmployees")
    public ResponseEntity<List<Employee>> createEmployees(@RequestBody List<Employee> emps)
    {
        try {
            for(Employee emp:emps)
            {
                emp.setPassword(passwordEncoder.encode(emp.getPassword()));
            }
            List<Employee> employees = this.employeeService.createEmployeesService(emps);
            if (employees.size() == 0) ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return new ResponseEntity<>(employees, HttpStatus.CREATED);

        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
