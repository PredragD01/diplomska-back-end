package com.mailautomation.mailautomation.service;

import com.mailautomation.mailautomation.dtos.EmployeeDto;
import com.mailautomation.mailautomation.dtos.ManagerUpdate;
import com.mailautomation.mailautomation.entities.Department;
import com.mailautomation.mailautomation.entities.Employee;
import com.mailautomation.mailautomation.entities.Managers;

import java.util.List;

public interface EmployeeService {
    EmployeeDto login(Employee emp);
    List<Employee> getManagers();
    void deleteEmp(Employee employee);
    void addEmp(Employee employee);
    List<EmployeeDto> getEmps();;
    void updateEmp(Employee employee);
    List<Department> getDeps();
    void updateManagers(Managers managers);
    void addManagers(ManagerUpdate managerUpdate);
}
