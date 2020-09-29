package com.mailautomation.mailautomation.repositories;

import com.mailautomation.mailautomation.dtos.EmployeeDto;
import com.mailautomation.mailautomation.dtos.ManagerUpdate;
import com.mailautomation.mailautomation.dtos.Manager_info;
import com.mailautomation.mailautomation.entities.Department;
import com.mailautomation.mailautomation.entities.Employee;
import com.mailautomation.mailautomation.entities.Managers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryServiceImpl implements EmployeeRepositoryService{
    @Autowired
    private EntityManager em;


    @Override
    public EmployeeDto login(Employee employee) {
        Query q = em.createNativeQuery("select * from employee WHERE email = ? and employee_password = ?", Employee.class)
                .setParameter(1, employee.getEmail())
                .setParameter(2, employee.getEmployee_password());
        Employee emp = (Employee) q.getSingleResult();
        Department dep = em.find(Department.class, emp.getDept());
        EmployeeDto empdto = new EmployeeDto();
        empdto.setDeptInfo(dep);
        empdto.setEmployee(emp);
        return empdto;
    }

    @Override
    public EmployeeDto getEmployeeByEmail(String email) {
        Query q = em.createNativeQuery("select * from employee WHERE email = ?", Employee.class)
                .setParameter(1, email);
        Employee emp = (Employee) q.getSingleResult();
        Department dep = em.find(Department.class, emp.getDept());
        EmployeeDto empdto = new EmployeeDto();
        empdto.setDeptInfo(dep);
        empdto.setEmployee(emp);
        return empdto;
    }




    @Override
    public int getManagerOfEmployee(int id) {
        Query q = em.createNativeQuery("SELECT managed_by from managers where employee = ?")
                .setParameter(1, id);
        int manager = id;
        try {
            manager = (Integer) q.getSingleResult();
        }catch (Exception e){
            System.out.println("NEMA i manager kje e IDto");
        }

        return manager;
    }

    @Override
    public int getHR() {
        Query q = em.createNativeQuery("select * from employee where dept = '1' limit 1", Employee.class);
        Employee employee = (Employee) q.getSingleResult();
        return employee.getId();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return em.find(Employee.class, id);
    }

    @Override
    public List<Employee> getManagers() {
        Query q = em.createNativeQuery("select * from employee where dept = 4", Employee.class);
        return (List<Employee>) q.getResultList();
    }

    @Transactional
    @Override
    public void addEmp(Employee emp){
        em.persist(emp);

    }

    @Transactional
    @Override
    public void deleteEmp(Employee employee) {
        Employee employee1 = em.merge(employee);
        em.remove(employee1);
    }

    @Transactional
    @Override
    public void updateEmp(Employee employee) {
        String pass = em.find(Employee.class, employee.getId()).getEmployee_password();
        employee.setEmployee_password(pass);
        em.merge(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        Query q = em.createNativeQuery("select * from employee", Employee.class);
        List<Employee> list = q.getResultList();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for(Employee emp : list){
            int manager = getManagerOfEmployee(emp.getId());
            Employee empManager = em.find(Employee.class, manager);
            Manager_info manager_info = new Manager_info(empManager.getEmployee_name(), empManager.getEmployee_surname(), empManager.getId());
            Department department = em.find(Department.class, emp.getDept());
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setDeptInfo(department);
            employeeDto.setEmployee(emp);
            if(emp.getId() != empManager.getId()){
                employeeDto.setManager_info(manager_info);
            }
            employeeDtos.add(employeeDto);
        }
        return employeeDtos;
    }



    @Override
    public List<Department> getDeps() {
        Query q = em.createNativeQuery("select * from department", Department.class);
        return (List<Department>) q.getResultList();
    }

    @Transactional
    @Override
    public void updateManagers(Managers managers) {
            em.merge(managers);
    }

    @Transactional
    @Override
    public void addManagers(ManagerUpdate managerUpdate){
        EmployeeDto employeeDto = getEmployeeByEmail(managerUpdate.getEmail());
        Managers managers_ent  = new Managers();
        managers_ent.setEmployee(employeeDto.getEmployee().getId());
        managers_ent.setManaged_by(managerUpdate.getManaged_by());
        em.persist(managers_ent);
    }
}
