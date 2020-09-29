package com.mailautomation.mailautomation.dtos;

import com.mailautomation.mailautomation.entities.Department;
import com.mailautomation.mailautomation.entities.Employee;


public class EmployeeDto {

    private Department deptInfo;

    private Employee employee;

    private Manager_info manager_info;
    public EmployeeDto() {

    }

    public Manager_info getManager_info() {
        return manager_info;
    }

    public void setManager_info(Manager_info manager_info) {
        this.manager_info = manager_info;
    }

    public Department getDeptInfo() {
        return deptInfo;
    }

    public void setDeptInfo(Department deptInfo) {
        this.deptInfo = deptInfo;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

