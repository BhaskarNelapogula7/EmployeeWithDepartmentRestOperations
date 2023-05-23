package com.app.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.entity.Department;
import com.app.entity.Employee;
import com.app.repository.DepartmentRepository;
import com.app.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@Mock
	private DepartmentRepository departmentRepository;

	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;

	@Test
	public void saveEmployeeWithDepartmentTest() {

		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("bhaskar");
		employee.setDesignation("IT");
		employee.setTechStack("java");
		employee.setJoinDate(new Date());
		employee.setSalary(40000.0);
		employee.setStatus("active");
		
		Integer departmentId = 1;
		Department department = new Department();
		department.setId(departmentId);
//		Optional<Department> optDepartment = Optional.of(department);
//		when(departmentRepository.findById(departmentId)).thenReturn(optDepartment);
		when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
		when(employeeRepository.save(employee)).thenReturn(employee);

		Employee savedEmployee = employeeServiceImpl.saveEmployeeWithDepartment(employee, departmentId);

		assertEquals(employee,savedEmployee);
		assertThat(savedEmployee).isNotNull();
		assertTrue(employee.getDepartment().contains(department));
		assertEquals(1, savedEmployee.getDepartment().size());
		verify(employeeRepository, times(1)).save(employee);
	}

	@Test
	public void getEmployeeByIdTest() {

		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("bhaskar");
		employee.setDesignation("IT");
		employee.setTechStack("java");
		employee.setJoinDate(new Date());
		employee.setSalary(40000.0);
		employee.setStatus("active");

		when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
		Employee saveEmployee = employeeServiceImpl.getEmployeeById(1);

		assertNotNull(1);
		assertThat(saveEmployee).isNotNull();
		assertEquals(1, saveEmployee.getId());
		assertEquals("bhaskar", saveEmployee.getName());
	}

	@Test
	public void findByNameTest() {

		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("bhaskar");
		employee.setDesignation("IT");
		employee.setTechStack("java");
		employee.setJoinDate(new Date());
		employee.setSalary(40000.0);
		employee.setStatus("active");

		when(employeeRepository.findByName(employee.getName())).thenReturn(Optional.of(employee));

		Employee saveEmployee = employeeServiceImpl.findByName("bhaskar");

		assertThat(saveEmployee).isEqualTo(employee);

	}

	@Test
	public void getAllEmployeeTest() {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("bhaskar");
		employee.setDesignation("IT");
		employee.setTechStack("java");
		employee.setJoinDate(new Date());
		employee.setSalary(40000.0);
		employee.setStatus("active");

		Employee employee1 = new Employee();
		employee1.setId(2);
		employee1.setName("rajesh");
		employee1.setDesignation("IT");
		employee1.setTechStack("java");
		employee1.setJoinDate(new Date());
		employee1.setSalary(40000.0);
		employee1.setStatus("active");

		when(employeeRepository.findAll()).thenReturn(List.of(employee, employee1));

		List<Employee> employeeList = employeeServiceImpl.getAllEmployees();
		assertThat(employeeList).isNotNull();

		assertThat(employeeList.size()).isEqualTo(2);
	}

	@Test
	public void updateEmployeeTest() {

		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("bhaskar");
		employee.setDesignation("IT");
		employee.setTechStack("java");
		employee.setJoinDate(new Date());
		employee.setSalary(40000.0);
		employee.setStatus("active");

		given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));
		when(employeeRepository.save(employee)).thenReturn(employee);
		employee.setName("sagar");
		employee.setTechStack("Fullstack");

		Employee updateEmployee = employeeServiceImpl.updateEmployee(employee.getId(), employee);

		assertThat(updateEmployee.getName()).isEqualTo("sagar");
		assertThat(updateEmployee.getTechStack()).isEqualTo("Fullstack");

	}

	@Test
	public void deleteEmployeeTest() {

		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("bhaskar");
		employee.setDesignation("IT");
		employee.setTechStack("java");
		employee.setJoinDate(new Date());
		employee.setSalary(40000.0);
		employee.setStatus("active");

		when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

		assertDoesNotThrow(() -> employeeServiceImpl.deleteEmployee(1));

		employeeServiceImpl.deleteEmployee(1);
		assertNotNull(1);
	}
}
