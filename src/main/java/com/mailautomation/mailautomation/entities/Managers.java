package com.mailautomation.mailautomation.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "managers")
public class Managers implements Serializable {

    @Id
    @Column
    private int id;

    @Column
    private Integer  employee;

    @Column
    private int managed_by;


    public Managers(){}
    public Managers(int id,Integer  employee, int managed_by) {
        this.employee = employee;
        this.managed_by = managed_by;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(Integer  employee) {
        this.employee = employee;
    }

    public int getManaged_by() {
        return managed_by;
    }

    public void setManaged_by(int managed_by) {
        this.managed_by = managed_by;
    }
}
