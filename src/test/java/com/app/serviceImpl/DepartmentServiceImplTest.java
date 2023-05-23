package com.app.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.entity.Department;

import com.app.repository.DepartmentRepository;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

	@Mock
	private DepartmentRepository departmentRepository;

	@InjectMocks
	private DepartmentServiceImpl departmentServiceImpl;

	@Test
	public void saveDepartmentTest() {

		Department department = new Department();
		department.setId(1);
		department.setName("javaPractice");

		when(departmentRepository.save(department)).thenReturn(department);

		Department saveDepartment = departmentServiceImpl.saveDepartment(department);

		assertThat(saveDepartment).isNotNull();
		assertEquals(1, saveDepartment.getId());
		assertEquals("javaPractice", saveDepartment.getName());
	}

	@Test
	public void getDepartmentByIdTest() {

		Department department = new Department();
		department.setId(1);
		department.setName("CSC");

		when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
		Department savedepartment = departmentServiceImpl.getDepartmentById(1);

		assertNotNull(1);
		assertThat(savedepartment).isNotNull();
		assertEquals(1, savedepartment.getId());
		assertEquals("CSC", savedepartment.getName());
	}

	@Test
	public void findByNameTest() {

		Department department = new Department();
		department.setId(1);
		department.setName("CSC");

		when(departmentRepository.findByName(department.getName())).thenReturn(Optional.of(department));

		Department saveDepartment = departmentServiceImpl.findByName("CSC");

		assertThat(saveDepartment).isEqualTo(department);
	}

	@Test
	public void getAllDepartmentTest() {
		Department department = new Department();
		department.setId(1);
		department.setName("CSC");

		Department department1 = new Department();
		department1.setId(2);
		department1.setName("javaPractice");

		when(departmentRepository.findAll()).thenReturn(List.of(department, department1));

		List<Department> departmentList = departmentServiceImpl.getAllDepartments();
		assertThat(departmentList).isNotNull();

		assertThat(departmentList.size()).isEqualTo(2);
	}

	@Test
	public void updateDepartmentTest() {

		Department department = new Department();
		department.setId(1);
		department.setName("CSC");

		given(departmentRepository.findById(department.getId())).willReturn(Optional.of(department));
		when(departmentRepository.save(department)).thenReturn(department);
		department.setName("javaPractice");

		Department updateEmployee = departmentServiceImpl.updateDepartment(department.getId(), department);

		assertThat(updateEmployee.getName()).isEqualTo("javaPractice");

	}

	@Test
	public void deleteDepartmentTest() {

		Department department = new Department();
		department.setId(1);
		department.setName("CSC");

		when(departmentRepository.findById(1)).thenReturn(Optional.of(department));

		assertDoesNotThrow(() -> departmentServiceImpl.deleteDepartment(1));

		departmentServiceImpl.deleteDepartment(1);
		assertNotNull(1);
	}
}
