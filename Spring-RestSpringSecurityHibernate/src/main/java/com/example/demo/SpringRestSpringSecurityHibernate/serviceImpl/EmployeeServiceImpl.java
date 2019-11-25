package com.example.demo.SpringRestSpringSecurityHibernate.serviceImpl;

import com.example.demo.SpringRestSpringSecurityHibernate.dao.EmployeeRepository;
import com.example.demo.SpringRestSpringSecurityHibernate.entity.Employee;
import com.example.demo.SpringRestSpringSecurityHibernate.entity.EmployeeIdentity;
import com.example.demo.SpringRestSpringSecurityHibernate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }

    @Override
    public Employee findById(EmployeeIdentity employeeIdentity) {
        Optional<Employee> employeeFound = employeeRepository.findById(employeeIdentity);
        Employee employee = null;
        if(employeeFound.isPresent()){
            employee = employeeFound.get();
        }
        else{
            throw new RuntimeException("Did not find employee id - " + employeeIdentity);
        }
        return employee;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Employee theEmployee = employeeRepository.save(employee);

        return theEmployee;
    }

    @Override
    public String deleteEmployee(EmployeeIdentity employeeIdentity) {
        Employee employee = findById(employeeIdentity);
        if(employee == null){
            return "Employee with Id "+ employeeIdentity+ " not found";
        }else{
            employeeRepository.delete(employee);
            return "Employee with ID "+ employeeIdentity + " deleted successfully!";
        }

    }
}
