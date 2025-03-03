package com.training.dao;

import com.training.model.Employee;
import java.util.List;

public interface EmployeeDao {
    int addEmployee(Employee employee);
    Employee getEmployeeById(long id);
    List<Employee> getAllEmployees();
    int updateEmployee(Employee employee);
    int deleteEmployee(long id);
}
