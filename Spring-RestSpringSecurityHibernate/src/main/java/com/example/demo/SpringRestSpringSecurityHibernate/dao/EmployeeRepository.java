package com.example.demo.SpringRestSpringSecurityHibernate.dao;

import com.example.demo.SpringRestSpringSecurityHibernate.entity.Employee;
import com.example.demo.SpringRestSpringSecurityHibernate.entity.EmployeeIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, EmployeeIdentity> {
}
