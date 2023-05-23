package com.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Department;
import com.app.entity.Employee;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.DepartmentRepository;
import com.app.repository.EmployeeRepository;
import com.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Employee saveEmployeeWithDepartment(Employee empObj, Integer depId) {//, Integer depId

		Department department = departmentRepository.findById(depId)

				.orElseThrow(() -> new ResourceNotFoundException("Record not found with id : " + depId));

		 empObj.getDepartment().add(department);

			return employeeRepo.save(empObj);
	
	}

	@Override
	public Employee getEmployeeById(Integer id) {

		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found with id : " + id));
		
		System.out.println("fetcting employee by id"+employee);
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {

		return employeeRepo.findAll();
	}

	@Override
	public void deleteEmployee(Integer id) {

		 Employee existingEmployee = employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found with id : " + id));
		 
		employeeRepo.delete(existingEmployee);
	}

	@Override
	public Employee findByName(String name) {

		return employeeRepo.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found with name : " + name));

	}

	@Override
	public Employee updateEmployee(Integer id, Employee empObj) {

		Employee existingEmployee = employeeRepo.findById(id)

				.orElseThrow(() -> new ResourceNotFoundException("Record not found with id : " + id));

//		Department depertament = departmentRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Record not found with id : " + id));

		existingEmployee.setName(empObj.getName());
		existingEmployee.setDesignation(empObj.getDesignation());
		existingEmployee.setTechStack(empObj.getTechStack());
		existingEmployee.setJoinDate(empObj.getJoinDate());
		existingEmployee.setSalary(empObj.getSalary());
		existingEmployee.setStatus(empObj.getStatus());

//			Department department = new Department();
//			department.setName(empObj.getDepartment().getName());
//			
//			existingEmployee.get().setDepartment(department);

		// depertament.setName(empObj.getDepartment().getClass().getName());

		return employeeRepo.save(existingEmployee);
	}
}
