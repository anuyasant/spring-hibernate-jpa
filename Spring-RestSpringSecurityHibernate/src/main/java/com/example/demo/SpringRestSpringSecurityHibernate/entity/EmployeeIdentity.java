package com.example.demo.SpringRestSpringSecurityHibernate.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeeIdentity implements Serializable {
    @NotNull
    @Size(max = 20)
    @Column(name = "company_id")
    private String companyId;

    @NotNull
    @Size(max = 20)
    @Column(name = "employee_id")
    private String employeeId;

    public EmployeeIdentity(){
    }

    public EmployeeIdentity(@NotNull @Size(max = 20) String companyId, @NotNull @Size(max = 20) String employeeId) {
        this.companyId = companyId;
        this.employeeId = employeeId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeIdentity)) return false;
        EmployeeIdentity that = (EmployeeIdentity) o;
        return getCompanyId().equals(that.getCompanyId()) &&
                getEmployeeId().equals(that.getEmployeeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCompanyId(), getEmployeeId());
    }
}
