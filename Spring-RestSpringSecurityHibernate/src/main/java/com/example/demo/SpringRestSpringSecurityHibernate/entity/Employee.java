package com.example.demo.SpringRestSpringSecurityHibernate.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

    @EmbeddedId
    private EmployeeIdentity employeeIdentity;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    public Employee() {
    }

    public Employee(EmployeeIdentity employeeIdentity, String firstName, String lastName, String email) {
        this.employeeIdentity = employeeIdentity;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public EmployeeIdentity getEmployeeIdentity() {
        return employeeIdentity;
    }

    public void setEmployeeIdentity(EmployeeIdentity employeeIdentity) {
        this.employeeIdentity = employeeIdentity;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
