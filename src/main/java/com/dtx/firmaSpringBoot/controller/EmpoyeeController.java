package com.dtx.firmaSpringBoot.controller;

import com.dtx.firmaSpringBoot.model.Employee;
import com.dtx.firmaSpringBoot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmpoyeeController {
    @Autowired
    EmployeeRepository employeeRepository;
//    @GetMapping("/test")
//    public int test(){
//        return 1;
//    }
    @GetMapping("")
    public List<Employee> getAll(){
        return employeeRepository.getAll();
    }
    @GetMapping("/{id}")
    public Employee getById(@PathVariable("id") int id) {
        return employeeRepository.getById(id);
    }
    @PostMapping("")
    public int add(@RequestBody List<Employee> employees) {
        return employeeRepository.save(employees);
    }
    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Employee updatedEmployee) {
        Employee employee = employeeRepository.getById(id);

        if (employee != null) {
            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setPhoneNumber(updatedEmployee.getPhoneNumber());

            employeeRepository.update(employee);

            return 1;
        } else {
            return -1;
        }
    }
    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Employee updatedEmployee) {
        Employee employee = employeeRepository.getById(id);

        if (employee != null) {
            if (updatedEmployee.getFirstName() != null) employee.setFirstName(updatedEmployee.getFirstName());
            if (updatedEmployee.getLastName() != null) employee.setLastName(updatedEmployee.getLastName());
            if (updatedEmployee.getEmail() != null) employee.setEmail(updatedEmployee.getEmail());
            if (updatedEmployee.getPhoneNumber() != null) employee.setPhoneNumber(updatedEmployee.getPhoneNumber());

            employeeRepository.update(employee);

            return 1;
        } else {
            return -1;
        }
    }
    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return employeeRepository.delete(id);
    }
}
