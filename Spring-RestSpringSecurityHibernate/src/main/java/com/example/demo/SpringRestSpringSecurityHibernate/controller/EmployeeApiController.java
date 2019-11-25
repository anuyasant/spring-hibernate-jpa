package com.example.demo.SpringRestSpringSecurityHibernate.controller;

import com.example.demo.SpringRestSpringSecurityHibernate.entity.Employee;
import com.example.demo.SpringRestSpringSecurityHibernate.entity.EmployeeIdentity;
import com.example.demo.SpringRestSpringSecurityHibernate.service.EmployeeService;
import com.example.demo.SpringRestSpringSecurityHibernate.util.CustomErrorType;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeApiController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeApiController(@Qualifier("employeeServiceImpl") EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        System.out.println("GEt all employees!");
        List<Employee> employeeList = employeeService.findAll();
        if(employeeList == null){
            return new ResponseEntity(new CustomErrorType("No Employees found!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity <Employee> getEmployee(@PathVariable String employeeId){
        EmployeeIdentity employeeIdentity = convertToEmployeeidentity(employeeId);
        Employee employee = employeeService.findById(employeeIdentity);
        if(employee == null){
            return new ResponseEntity(new CustomErrorType("No Employee found!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee employeePresent = employeeService.findById(employee.getEmployeeIdentity());
        if(employeePresent != null){
            return new ResponseEntity(new CustomErrorType("Employee already present!"), HttpStatus.ALREADY_REPORTED);
        }
        else{
            employee.setEmployeeIdentity(null);
            Employee employeeAdded = employeeService.saveEmployee(employee);
            return new ResponseEntity<Employee>(employeeAdded, HttpStatus.OK);
        }

    }

    @PutMapping("/employee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        Employee employeePresent = employeeService.findById(employee.getEmployeeIdentity());
        if(employeePresent != null){
            return new ResponseEntity(new CustomErrorType("Employee not found!"), HttpStatus.NOT_FOUND);
        }
        else{
            Employee employeeUpdated = employeeService.saveEmployee(employee);
            return new ResponseEntity<Employee>(employeeUpdated, HttpStatus.OK);
        }

    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<String> updateEmployee(@PathVariable String employeeId){
        EmployeeIdentity employeeIdentity = convertToEmployeeidentity(employeeId);
        String result = employeeService.deleteEmployee(employeeIdentity);
        return new ResponseEntity<String> (result, HttpStatus.OK);
    }

    private EmployeeIdentity convertToEmployeeidentity(String string){
        EmployeeIdentity employeeIdentity = new EmployeeIdentity();
        int companyIdIndex = string.indexOf("c_id=");
        int employeeIdIndex = string.indexOf("e_id=");
        try {
            if((companyIdIndex== -1) || (employeeIdIndex == -1)) {
                String s = "Incorrect Id values passed!";
                //throw new Exception("Incorrect Id values passed!");
            }
            else {
                String company_Id = string.substring(companyIdIndex + "c_id=".length(), employeeIdIndex);
                String employee_Id = string.substring(employeeIdIndex + "e_id=".length());
                employeeIdentity.setCompanyId(company_Id);
                employeeIdentity.setEmployeeId(employee_Id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employeeIdentity;
    }

}
