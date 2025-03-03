package com.training.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.dao.EmployeeDao;
import com.training.model.Employee;

@Controller
public class EmployeeController {
    private final EmployeeDao employeeDao;

    public EmployeeController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
    
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // **1️⃣ Fetch and Display Employee List**
    @GetMapping("/employees")
    public String showEmployees(Model model) {
        List<Employee> employees = employeeDao.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employee-list";
    }

    // **2️⃣ Show Add Employee Form**
    @GetMapping("/employees/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    // **3️⃣ Add Employee**
    @PostMapping("/employees/save")
    public String addEmployee(@ModelAttribute Employee employee,@RequestParam("name") String name) {
    	System.out.println(employee);
        employee.setJoinedAt(LocalDateTime.now());
        employeeDao.addEmployee(employee);
        return "redirect:/employees";
    }

    // **4️⃣ Show Edit Employee Form**
    @GetMapping("/employees/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    // **5️⃣ Update Employee**
    @PostMapping("/employees/update/{id}")
    public String updateEmployee(@PathVariable("id") long id, @ModelAttribute Employee employee) {
        employee.setEmpId(id);
        employeeDao.updateEmployee(employee);
        return "redirect:/employees";
    }

    // **6️⃣ Delete Employee**
    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id) {
        employeeDao.deleteEmployee(id);
        return "redirect:/employees";
    }
}
