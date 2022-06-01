package com.example.RestApi.service;

import com.example.RestApi.dao.EmployeeRepository;
import com.example.RestApi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployeesService()
    {
        return this.employeeRepository.findAll();
    }

    public Employee getEmployeeByIdService(Long id)
    {
        Optional<Employee> temp=this.employeeRepository.findById(id);
        if(temp.isPresent()) {
            Employee e = temp.get();
            return e;
        }
        return null;
    }

    public List<Employee> getEmployeesByDepartmentService(String department)
    {
        return this.employeeRepository.findByDepartment(department);
    }

    public Employee createEmployeeService(Employee emp)
    {
        if(emp.getName()==null || emp.getAge()<=0 || emp.getDepartment()==null || emp.getEmail()==null || emp.getPassword()==null || emp.getRole()==null)
        {
            return null;
        }
        return this.employeeRepository.save(emp);
    }

    public List<Employee> createEmployeesService(List<Employee> emps)
    {
        for(Employee emp:emps)
        {
            if(emp.getName()==null || emp.getAge()<=0 || emp.getDepartment()==null || emp.getEmail()==null || emp.getPassword()==null || emp.getRole()==null)
            {
                return new ArrayList<Employee>();
            }
        }
        return this.employeeRepository.saveAll(emps);
    }

    public Employee updateEmployeeService(Employee emp,Long id)
    {
            Optional<Employee> temp = this.employeeRepository.findById(id);
            if(temp.isPresent()) {
                Employee e = temp.get();
                if (emp.getAge() > 0) e.setAge(emp.getAge());
                if (emp.getDepartment() != null) e.setDepartment(emp.getDepartment());
                if (emp.getName() != null) e.setName(emp.getName());
                if(emp.getEmail()!=null) e.setEmail(emp.getEmail());
                if(emp.getPassword()!=null) e.setPassword(emp.getPassword());
                if(emp.getRole()!=null)e.setRole(emp.getRole());
                this.employeeRepository.save(e);
                return e;
            }
            else
            {
                return null;
            }


    }

//    public void deleteEmployeeByIdService(Long id)
//    {
//        this.employeeRepository.deleteById(id);
//    }
//
//    public void deleteEmployeesService()
//    {
//        this.employeeRepository.deleteAll();
//    }


}
