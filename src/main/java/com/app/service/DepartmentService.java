package com.app.service;

import java.util.List;

import com.app.entity.Department;

public interface DepartmentService {
	
	public Department saveDepartment(Department department);
	
	public Department getDepartmentById(Integer id);
	
	public Department findByName(String name);

	public List<Department> getAllDepartments();

	public void deleteDepartment(Integer id);

	public Department updateDepartment(Integer id,Department department);


}
