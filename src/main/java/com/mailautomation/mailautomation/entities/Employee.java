package com.mailautomation.mailautomation.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity(name = "employee")
public class Employee {
    @Id
    @Column
    private int id;

    @Column
    private String employee_name;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String employee_password;

    @Column
    private String email;

    @Column
    private String address_street;

    @Column
    private String city;

    @Column
    private int dept;

    @Column
    private String employee_surname;

    @Column
    private String gender;

    public Employee(){}

    public Employee(String employee_name, String employee_password, String email, String address_street, String city, int dept, String employee_surname, String gender) {
        this.employee_name = employee_name;
        this.employee_password = employee_password;
        this.email = email;
        this.address_street = address_street;
        this.city = city;
        this.dept = dept;
        this.employee_surname = employee_surname;
        this.gender = gender;
    }

    public String getEmployee_surname() {
        return employee_surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmployee_surname(String employee_surname) {
        this.employee_surname = employee_surname;
    }

    public String getAddress_street() {
        return address_street;
    }

    public void setAddress_street(String address_street) {
        this.address_street = address_street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_password() {
        return employee_password;
    }

    public void setEmployee_password(String employee_password) {
        this.employee_password = employee_password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }
}
