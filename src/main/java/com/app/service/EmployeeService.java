package com.app.service;

import java.util.List;

import com.app.entity.Employee;

public interface EmployeeService {
	
	public Employee saveEmployeeWithDepartment(Employee employee, Integer depId);//

	public Employee getEmployeeById(Integer id);
	
	public Employee findByName(String name);

	public List<Employee> getAllEmployees();

	public void deleteEmployee(Integer id);

	public Employee updateEmployee(Integer id,Employee employee);

}
