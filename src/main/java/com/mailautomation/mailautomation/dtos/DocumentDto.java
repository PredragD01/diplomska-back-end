package com.mailautomation.mailautomation.dtos;

import com.mailautomation.mailautomation.entities.Documents;
import com.mailautomation.mailautomation.entities.Employee;

public class DocumentDto {
    Employee emp;
    Documents doc;
    public DocumentDto(){}
    public DocumentDto(Employee emp, Documents doc) {
        this.emp = emp;
        this.doc = doc;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public Documents getDoc() {
        return doc;
    }

    public void setDoc(Documents doc) {
        this.doc = doc;
    }
}
