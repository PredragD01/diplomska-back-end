package com.mailautomation.mailautomation.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "department")
public class Department {

    @Id
    @Column
    private Integer id;

    @Column
    private String department_name;

    public Department(){}

    public Department(int employee, String managed_by) {
        this.id = employee;
        this.department_name = managed_by;
    }

    public int getId() {
        return id;
    }

    public void setId(int employee) {
        this.id = employee;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String managed_by) {
        this.department_name = managed_by;
    }
}
