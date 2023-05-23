package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Department;
import com.app.entity.Employee;
import com.app.serviceImpl.DepartmentServiceImpl;

@RestController
@RequestMapping(value="/department")//, produces = MediaType.APPLICATION_JSON_VALUE
public class DepartmentController {
	
	@Autowired
	private DepartmentServiceImpl departmentServiceImpl;
	
	@PostMapping
	public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {

		Department saveDepartment = departmentServiceImpl.saveDepartment(department);

		return new ResponseEntity<Department>(saveDepartment, HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Integer id) {

		Department department =  departmentServiceImpl.getDepartmentById(id);
		
		System.out.println("employee saved successfully"+department);
		
		return new ResponseEntity<Department>(department, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<Department>> getAllDepartments() {

		List<Department> allDepartments = departmentServiceImpl.getAllDepartments();

		return new ResponseEntity<List<Department>>(allDepartments, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDepartment(@PathVariable Integer id) {

		departmentServiceImpl.deleteDepartment(id);

		return new ResponseEntity<>("Deleted Department id=" + id + " successfully", HttpStatus.OK);
	}

	@GetMapping("/{name}")
	public ResponseEntity<Department> findByName(@PathVariable String name) {

		Department department = departmentServiceImpl.findByName(name);

		return new ResponseEntity<Department>(department, HttpStatus.OK);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable Integer id, @RequestBody Department department) {

		Department updateDepartment = departmentServiceImpl.updateDepartment(id, department);

		return new ResponseEntity<Department>(updateDepartment, HttpStatus.OK);

	}

}
