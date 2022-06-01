package com.example.RestApi.service;

import com.example.RestApi.configuration.CustomEmployeeDetails;
import com.example.RestApi.dao.EmployeeRepository;
import com.example.RestApi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee emp=employeeRepository.findByEmail(username);
        if(emp==null) throw new UsernameNotFoundException("Employee not found");
        CustomEmployeeDetails customEmployeeDetails=new CustomEmployeeDetails(emp);
        return customEmployeeDetails;
    }
}
