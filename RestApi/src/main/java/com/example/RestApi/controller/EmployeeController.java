package com.example.RestApi.controller;

import com.example.RestApi.model.Employee;
import com.example.RestApi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> emps=null;
        try {
            emps = this.employeeService.getAllEmployeesService();
            if(emps.size()==0) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return new ResponseEntity<>(emps, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id)
    {
        Employee emp=null;
        try
        {
            emp=this.employeeService.getEmployeeByIdService(id);
            if(emp==null)ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return new ResponseEntity<>(this.employeeService.getEmployeeByIdService(id),HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/employees/{department}")
    public ResponseEntity<List<Employee>> getEmployeeByDepartment(@PathVariable("department") String department)
    {
        try {
            List<Employee> emps = this.employeeService.getEmployeesByDepartmentService(department);
            if (emps.size() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return new ResponseEntity<>(emps, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

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

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp,@PathVariable("id") Long id)
    {
        try {
            Employee employee = this.employeeService.updateEmployeeService(emp, id);
            if (employee == null) ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return new ResponseEntity<>(this.employeeService.updateEmployeeService(emp, id), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @DeleteMapping("deleteEmployee/{id}")
//    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id)
//    {
//        try {
//            this.employeeService.deleteEmployeeByIdService(id);
//            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
//        }
//        catch (Exception e)
//        {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @DeleteMapping("deleteEmployees")
//    public ResponseEntity<String> deleteEmployees()
//    {
//        try {
//            this.employeeService.deleteEmployeesService();
//            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
//        }
//        catch (Exception e)
//        {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

}
