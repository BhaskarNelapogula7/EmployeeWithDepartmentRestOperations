package com.app.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.entity.Employee;

@DataJpaTest
class EmployeeRepositoryTest {

	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	public void saveEmployeeTest() {

		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("bhaskar");
		employee.setDesignation("IT");
		employee.setTechStack("java");
		employee.setJoinDate(new Date());
		employee.setSalary(40000.0);
		employee.setStatus("active");

		Employee saveEmployee = employeeRepository.save(employee);

		Assertions.assertNotNull(saveEmployee.getId());
		assertThat(saveEmployee).isNotNull();
		assertThat(saveEmployee.getId()).isGreaterThan(0);

	}

	@Test
	public void getEmployeeByIdTest() {

		Employee employee = new Employee();
		employee.setId(3);
		employee.setName("bhaskar");
		employee.setDesignation("IT");
		employee.setTechStack("java");
		employee.setJoinDate(new Date());
		employee.setSalary(40000.0);
		employee.setStatus("active");

		employeeRepository.save(employee);

		Employee emp = employeeRepository.findById(3).get();

		assertNotNull(emp);
		assertEquals(employee.getId(), emp.getId());
		assertThat(emp).isNotNull();
	}

	@Test
	public void findByNameTest() {

		Employee employee = new Employee();
		employee.setId(3);
		employee.setName("Bhaskar");
		employee.setDesignation("IT");
		employee.setTechStack("java");
		employee.setJoinDate(new Date());
		employee.setSalary(40000.0);
		employee.setStatus("active");

		employeeRepository.save(employee);

		Employee emp = employeeRepository.findByName(employee.getName()).get();
		
		assertEquals(employee.getName(), emp.getName());
		assertThat(employee).isNotNull();
	}

	@Test
	public void getAllEmployees() {

		Employee employee = new Employee();
		employee.setId(3);
		employee.setName("Bhaskar");
		employee.setDesignation("IT");
		employee.setTechStack("java");
		employee.setJoinDate(new Date());
		employee.setSalary(40000.0);
		employee.setStatus("active");

		employeeRepository.save(employee);
		List<Employee> employeelist = employeeRepository.findAll();

		assertThat(employeelist).isNotNull();
		assertThat(employeelist.size()).isEqualTo(1);

	}

	@Test
	public void updateEmployeeTest() {

		Employee employee = new Employee();
		employee.setName("Bhaskar");
		employee.setDesignation("IT");
		employee.setTechStack("java");
		employee.setJoinDate(new Date());
		employee.setSalary(40000.0);
		employee.setStatus("active");
		employeeRepository.save(employee);

		Employee saveEmployee = employeeRepository.getById(employee.getId());
		saveEmployee.setName("sagar");
		saveEmployee.setTechStack("Fullstack");
		Employee updateEmployee = employeeRepository.save(saveEmployee);

		assertThat(updateEmployee.getName()).isEqualTo("sagar");
		assertThat(updateEmployee.getTechStack()).isEqualTo("Fullstack");
	}

	
	@Test
	public void deleteEmployeeTest() {

		Employee employee = new Employee();
		employee.setId(3);
		employee.setName("Bhaskar");
		employee.setDesignation("IT");
		employee.setTechStack("java");
		employee.setJoinDate(new Date());
		employee.setSalary(40000.0);
		employee.setStatus("active");

		employeeRepository.save(employee);

		employeeRepository.delete(employee);

		Optional<Employee> e1 = employeeRepository.findById(3);
		assertThat(e1).isEmpty();

	}
}
