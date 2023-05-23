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

import com.app.entity.Department;
import com.app.serviceImpl.DepartmentServiceImpl;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {

	@Mock
	private DepartmentServiceImpl departmentService;

	@InjectMocks
	private DepartmentController departmentController;

	@Test
	void getDepartmentByIdTest() {

		Department department = new Department();
		department.setId(1);
		department.setName("CSC");
		doReturn(department).when(departmentService).getDepartmentById(1);

		ResponseEntity<Department> result = departmentController.getDepartmentById(1);

		// verify(departmentService).getDepartmentById(1);

		assertNotNull(result);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(department, result.getBody());

	}

	@Test
	void getAllDepartmentTest() {
		List<Department> departments = new ArrayList<Department>();

		departments.add(new Department(2, "CSC"));

		departments.add(new Department(3, "javaPractice"));

		when(departmentService.getAllDepartments()).thenReturn(departments);

		ResponseEntity<List<Department>> response = departmentController.getAllDepartments();

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(departments, response.getBody());

	}

	@Test
	void saveDepartmentTest() {

		Department department = new Department(3, "CSC");

		when(departmentService.saveDepartment(department)).thenReturn(department);

		ResponseEntity<Department> response = departmentController.saveDepartment(department);

		// verify(departmentService).saveDepartment(department);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(department, response.getBody());

	}

	@Test
	void findByNameTest() {

		String name = "CSC";
		Department department = new Department(3, name);

		when(departmentService.findByName(name)).thenReturn(department);

		ResponseEntity<Department> response = departmentController.findByName(name);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(department, response.getBody());

		// verify(departmentService, times(1)).findByName(name);
	}

	@Test
	void updateDepartmentTest() {

		Integer deptId = 1;
		Department department = new Department(1, "CSC");
		Department updatedDepartment = new Department(1, "javaPractice");
//		when(departmentService.updateDepartment(deptId, updatedDepartment)).thenReturn(updatedDepartment);
//		ResponseEntity<Department> response = departmentController.updateDepartment(deptId, department);
//		// verify(employeeService).updateEmployee(empId, employee);
//		assertNotNull(response);
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals(updatedDepartment, response.getBody());
		
		
		when(departmentService.updateDepartment(deptId,department)).thenReturn(department);

        ResponseEntity<Department> response = departmentController.updateDepartment(deptId, department);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(department, response.getBody());

       //verify(departmentService, times(1)).updateDepartment(deptId, department);
    }
	

	@Test
	void deleteDepartmentTest() {

		Integer id = 1;

		ResponseEntity<?> response = departmentController.deleteDepartment(id);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Deleted Department id=" + id + " successfully", response.getBody());

		//verify(departmentService, times(1)).deleteDepartment(id);
	}
}
