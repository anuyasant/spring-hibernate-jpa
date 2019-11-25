package com.example.demo.SpringRestSpringSecurityHibernate.service;

import com.example.demo.SpringRestSpringSecurityHibernate.entity.Employee;
import com.example.demo.SpringRestSpringSecurityHibernate.entity.EmployeeIdentity;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();

    public Employee findById(EmployeeIdentity employeeIdentity);

    public Employee saveEmployee(Employee employee);

    public String deleteEmployee(EmployeeIdentity employeeIdentity);
}
