package com.training.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.training.dao.EmployeeDao;
import com.training.model.Employee;

/**
 * CREATE TABLE employees (
    emp_id SERIAL PRIMARY KEY,
    emp_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    mobile BIGINT NOT NULL,
    password VARCHAR(255) NOT NULL,
    gender CHAR(1) NOT NULL,
    is_married BOOLEAN NOT NULL,
    salary DOUBLE PRECISION NOT NULL,
    height REAL NOT NULL,
    dob DATE NOT NULL,
    joined_at TIMESTAMP NOT NULL
);

select * from employees;
 */


@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    private final JdbcTemplate jdbcTemplate;

    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (emp_name, email, mobile, password, gender, is_married, salary, height, dob, joined_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql, employee.getEmpName(), employee.getEmail(), employee.getMobile(),
                    employee.getPassword(), employee.getGender(), employee.isMarried(), employee.getSalary(),
                    employee.getHeight(), employee.getDob(), Timestamp.valueOf(employee.getJoinedAt()));
        } catch (DataAccessException e) {
            System.err.println("Error adding employee: " + e.getMessage());
            return 0; // Indicate failure
        }
    }

    @Override
    public int updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET emp_name=?, email=?, mobile=?, password=?, gender=?, is_married=?, salary=?, height=?, dob=? WHERE emp_id=?";
        try {
            return jdbcTemplate.update(sql, employee.getEmpName(), employee.getEmail(), employee.getMobile(),
                    employee.getPassword(), employee.getGender(), employee.isMarried(), employee.getSalary(),
                    employee.getHeight(), employee.getDob(), employee.getEmpId());
        } catch (DataAccessException e) {
            System.err.println("Error updating employee: " + e.getMessage());
            return 0; // Indicate failure
        }
    }

    @Override
    public int deleteEmployee(long id) {
        String sql = "DELETE FROM employees WHERE emp_id=?";
        try {
            return jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            System.err.println("Error deleting employee with ID " + id + ": " + e.getMessage());
            return 0; // Indicate failure
        }
    }

    // RowMapper
    private static class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee emp = new Employee();
            emp.setEmpId(rs.getLong("emp_id"));
            emp.setEmpName(rs.getString("emp_name"));
            emp.setEmail(rs.getString("email"));
            emp.setMobile(rs.getLong("mobile"));
            emp.setPassword(rs.getString("password"));
            emp.setGender(rs.getString("gender").charAt(0));
            emp.setMarried(rs.getBoolean("is_married"));
            emp.setSalary(rs.getDouble("salary"));
            emp.setHeight(rs.getFloat("height"));
            emp.setDob(rs.getDate("dob").toLocalDate());
            emp.setJoinedAt(rs.getTimestamp("joined_at").toLocalDateTime());
            return emp;
        }
    }

    @Override
    public Employee getEmployeeById(long id) {
        String sql = "SELECT * FROM employees WHERE emp_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("No employee found with ID: " + id);
            return null; // Return null if employee not found
        } catch (DataAccessException e) {
            System.err.println("Error fetching employee by ID " + id + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employees";
        try {
            return jdbcTemplate.query(sql, new EmployeeRowMapper());
        } catch (DataAccessException e) {
            System.err.println("Error retrieving employees: " + e.getMessage());
            return List.of(); // Return empty list on failure
        }
    }
}