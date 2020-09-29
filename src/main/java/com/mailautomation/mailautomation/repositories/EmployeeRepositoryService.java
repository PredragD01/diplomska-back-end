package com.mailautomation.mailautomation.repositories;

import com.mailautomation.mailautomation.dtos.EmployeeDto;
import com.mailautomation.mailautomation.dtos.ManagerUpdate;
import com.mailautomation.mailautomation.entities.Department;
import com.mailautomation.mailautomation.entities.Employee;
import com.mailautomation.mailautomation.entities.Managers;

import java.util.List;

public interface EmployeeRepositoryService {

    EmployeeDto login(Employee employee);
    EmployeeDto getEmployeeByEmail(String email);
    int getManagerOfEmployee(int id);
    int getHR();
    Employee getEmployeeById(int id);
    List<Employee> getManagers();
    void addEmp(Employee employee);
    void deleteEmp(Employee employee);
    void updateEmp(Employee employee);
    List<EmployeeDto> getAllEmployees();
    List<Department> getDeps();
    void updateManagers(Managers managers);
    void addManagers(ManagerUpdate managerUpdate);
}
