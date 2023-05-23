package com.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Department;
import com.app.entity.Employee;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.DepartmentRepository;
import com.app.repository.EmployeeRepository;
import com.app.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private EmployeeRepository employeeRepo;

	@Override
	public Department saveDepartment(Department department) {

		return departmentRepository.save(department);
	}

	@Override
	public Department getDepartmentById(Integer id) {
		return departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found with id : " + id));

	}

	@Override
	public List<Department> getAllDepartments() {

		return departmentRepository.findAll();
	}

	@Override
	public void deleteDepartment(Integer id) {
		Department existDepartment = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found with id : " + id));
		departmentRepository.delete(existDepartment);
	}

	@Override
	public Department findByName(String name) {
		return departmentRepository.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found with name : " + name));
	}

	@Override
	public Department updateDepartment(Integer id, Department department) {
		Department existDepartment = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found with name : " + id));
		existDepartment.setName(department.getName());

		return departmentRepository.save(existDepartment);
	}
}
