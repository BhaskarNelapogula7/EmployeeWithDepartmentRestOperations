package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Employee;
import com.app.serviceImpl.EmployeeServiceImpl;

@RestController
@RequestMapping(value="/employee")//, produces = MediaType.APPLICATION_JSON_VALUE
public class EmployeeController {

	
	@Autowired
	private EmployeeServiceImpl employeeService;

	@PostMapping("/{id}")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee, @PathVariable Integer id) {//, @PathVariable Integer id

		Employee saveEmployee = employeeService.saveEmployeeWithDepartment(employee,id);

		return new ResponseEntity<Employee>(saveEmployee, HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {

		Employee employee = employeeService.getEmployeeById(id);
		
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {

		List<Employee> allEmployees = employeeService.getAllEmployees();

		return new ResponseEntity<List<Employee>>(allEmployees, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {

		employeeService.deleteEmployee(id);

		return new ResponseEntity<>("Deleted employee id=" + id + " successfully", HttpStatus.OK);
	}

	@GetMapping("/{name}")
	public ResponseEntity<Employee> findByName(@PathVariable String name) {

		Employee employee = employeeService.findByName(name);

		return new ResponseEntity<Employee>(employee, HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {

		Employee updateEmployee = employeeService.updateEmployee(id, employee);

		return new ResponseEntity<Employee>(updateEmployee, HttpStatus.OK);

	}
	
}
