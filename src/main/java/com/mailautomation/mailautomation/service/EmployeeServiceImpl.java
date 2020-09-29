package com.mailautomation.mailautomation.service;

import com.mailautomation.mailautomation.dtos.EmployeeDto;
import com.mailautomation.mailautomation.dtos.ManagerUpdate;
import com.mailautomation.mailautomation.entities.Department;
import com.mailautomation.mailautomation.entities.Employee;
import com.mailautomation.mailautomation.entities.Managers;
import com.mailautomation.mailautomation.repositories.EmployeeRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepositoryService ers;

    @Override
    public EmployeeDto login(Employee emp) {
        return ers.login(emp);
    }

    @Override
    public List<Employee> getManagers() {
        return ers.getManagers();
    }

    @Override
    public void deleteEmp(Employee employee){
        ers.deleteEmp(employee);
    }

    @Override
    public void addEmp(Employee employee) {
        ers.addEmp(employee);
    }

    @Override
    public List<EmployeeDto> getEmps() {
        return ers.getAllEmployees();
    }

    @Override
    public void updateEmp(Employee employee){
        ers.updateEmp(employee);
    }

    @Override
    public List<Department> getDeps() {
        return ers.getDeps();
    }

    @Override
    public void updateManagers(Managers managers) {
        ers.updateManagers(managers);
    }

    @Override
    public void addManagers(ManagerUpdate managers) {
        ers.addManagers(managers);
    }
}
