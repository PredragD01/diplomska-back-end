package com.mailautomation.mailautomation.controllers;

import com.mailautomation.mailautomation.dtos.EmployeeDto;
import com.mailautomation.mailautomation.dtos.ManagerUpdate;
import com.mailautomation.mailautomation.entities.Department;
import com.mailautomation.mailautomation.entities.Employee;
import com.mailautomation.mailautomation.entities.Managers;
import com.mailautomation.mailautomation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public EmployeeDto login(@RequestBody Employee emp){
        return employeeService.login(emp);
    }

    @GetMapping("/managers")
    public List<Employee> getManagers(){
        return employeeService.getManagers();
    }

    @PostMapping("/emp")
    public void addEmp(@RequestBody Employee employee){
        employeeService.addEmp(employee);
    }

    @PostMapping("/empDel")
    public void delEmp(@RequestBody Employee employee){
        employeeService.deleteEmp(employee);
    }

    @PostMapping("/empUpd")
    public void updEmp(@RequestBody Employee employee){
        employeeService.updateEmp(employee);
    }

    @GetMapping("/emps")
    public List<EmployeeDto> getEmps(){
        return employeeService.getEmps();
    }

    @GetMapping("/getDeps")
    public List<Department> getDeps(){
        return employeeService.getDeps();
    }

    @PostMapping("/updateManager")
    public void updateManager(@RequestBody Managers managers){
        employeeService.updateManagers(managers);
    }

    @PostMapping("/addManagers")
    public void addManagers(@RequestBody ManagerUpdate managers){
        employeeService.addManagers(managers);
    }
}
