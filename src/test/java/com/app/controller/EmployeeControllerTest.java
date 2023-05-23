package com.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.entity.Employee;
import com.app.serviceImpl.EmployeeServiceImpl;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

	@Mock
	private EmployeeServiceImpl employeeService;

	@InjectMocks
	private EmployeeController employeeController;

	@Test
	void getEmployeeByIdTest() {

		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("bhaskar");
		employee.setDesignation("IT");
		employee.setTechStack("java");
		employee.setJoinDate(new Date());
		employee.setSalary(40000.0);
		employee.setStatus("active");
		

		doReturn(employee).when(employeeService).getEmployeeById(1);

		ResponseEntity<Employee> result = employeeController.getEmployeeById(1);

		// verify(employeeService).getEmployeeById(1);

		assertNotNull(result);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(employee, result.getBody());
	}

	@Test
	void getAllEmployeesTest() {
		List<Employee> employees = new ArrayList<Employee>();

		employees.add(new Employee(2, "bhaskar", "IT", "Java", new Date(), 40000.00, "active", null));

		employees.add(new Employee(3, "rajesh", "IT", "Java", new Date(), 50000.00, "active", null));

		when(employeeService.getAllEmployees()).thenReturn(employees);

		ResponseEntity<List<Employee>> response = employeeController.getAllEmployees();

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(employees, response.getBody());

	}

	@Test
	void saveEmployeeTest() {

		Employee employee = new Employee(3, "rajesh", "IT", "Java", new Date(), 50000.00, "active", null);

		when(employeeService.saveEmployeeWithDepartment(employee,3)).thenReturn(employee);

		ResponseEntity<Employee> response = employeeController.saveEmployee(employee,3);

		// verify(employeeService).saveEmployeeWithDepartment(employee);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(employee, response.getBody());

	}

	@Test
	void findByNameTest() {

		String name = "rajesh";
		Employee employee = new Employee(3, name, "IT", "Java", new Date(), 50000.00, "active", null);

		when(employeeService.findByName(name)).thenReturn(employee);

		ResponseEntity<Employee> response = employeeController.findByName(name);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(employee, response.getBody());

		// verify(employeeService, times(1)).findByName(name);
	}

	@Test
	void updateEmployeeTest() {

		Integer empId = 1;
		Employee employee = new Employee(1, "rajesh", "IT", "Java", new Date(), 50000.00, "active", null);
		Employee updatedEmployee = new Employee(1, "suresh", "IT", "Java", new Date(), 60000.00, "active", null);
		when(employeeService.updateEmployee(empId, employee)).thenReturn(updatedEmployee);
		ResponseEntity<Employee> response = employeeController.updateEmployee(empId, employee);
		//verify(employeeService).updateEmployee(empId, employee);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(updatedEmployee, response.getBody());
	}

	@Test
	void deleteEmployeeTest() {

		Integer id = 1;

		ResponseEntity<?> response = employeeController.deleteEmployee(id);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Deleted employee id=" + id + " successfully", response.getBody());

		verify(employeeService, times(1)).deleteEmployee(id);
	}
}
