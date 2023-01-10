package com.dtx.firmaSpringBoot.repository;

import com.dtx.firmaSpringBoot.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Employee> getAll(){
        return jdbcTemplate.query("SELECT * FROM employees",
                BeanPropertyRowMapper.newInstance(Employee.class));
    }
    public Employee getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, first_name, last_name, email, phone_number FROM employees WHERE " +
                "id = ?", BeanPropertyRowMapper.newInstance(Employee.class), id);
    }
    public int save(List<Employee> employees) {
        employees.forEach(employee -> jdbcTemplate
                .update("INSERT INTO employees(first_name, last_name, email, phone_number) VALUES(?, ?, ?, ?)",
                        employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPhoneNumber()
                ));

        return 1;
    }

    public int update(Employee employee) {
        return jdbcTemplate.update("UPDATE employees SET first_name=?, last_name=?, email=?, phone_number=? WHERE id=?",
                employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPhoneNumber(), employee.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM employees WHERE id=?", id);
    }
}
