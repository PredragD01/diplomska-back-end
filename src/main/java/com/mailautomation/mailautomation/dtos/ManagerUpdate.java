package com.mailautomation.mailautomation.dtos;

public class ManagerUpdate {
    private Integer  employee;

    private int managed_by;

    private String email;

    public ManagerUpdate(){}

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

    public int getManaged_by() {
        return managed_by;
    }

    public void setManaged_by(int managed_by) {
        this.managed_by = managed_by;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
