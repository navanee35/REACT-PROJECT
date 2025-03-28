package com.navanee.demo.controller;

import com.navanee.demo.model.Employee;
import com.navanee.demo.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.navanee.demo.model.Task;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping("/designation/{designation}")
    public ResponseEntity<List<Employee>> getEmployeesByDesignation(@PathVariable String designation) {
        return ResponseEntity.ok(employeeService.getEmployeesByDesignation(designation));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployeesByName(@RequestParam String keyword) {
        return ResponseEntity.ok(employeeService.searchEmployeesByName(keyword));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
    
        List<Employee> employees = employeeService.getAllEmployees(page, size, sortBy, sortDir);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Employee>> getEmployeesByTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(employeeService.getEmployeesByTask(taskId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateByName/{currentEmployeeName}")
    public ResponseEntity<String> updateEmployeeByName(@PathVariable String currentEmployeeName, @RequestBody Employee employee) {
        Task task = employee.getTask(); // Assuming task is part of the employee object
        int updatedRows = employeeService.updateEmployeeByName(currentEmployeeName, employee.getEmployeeName(), employee.getDesignation(), task);
        if (updatedRows > 0) {
            return ResponseEntity.ok("Employee updated successfully.");
        } else {
            return ResponseEntity.status(400).body("Employee not found or update failed.");
        }
    }

    @DeleteMapping("/deleteByName/{employeeName}")
    public ResponseEntity<String> deleteEmployeeByName(@PathVariable String employeeName) {
        int deletedRows = employeeService.deleteEmployeeByName(employeeName);
        if (deletedRows > 0) {
            return ResponseEntity.ok("Employee deleted successfully.");
        } else {
            return ResponseEntity.status(400).body("Employee not found or deletion failed.");
        }
    }
}
