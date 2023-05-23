package com.app.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.app.entity.Department;


@DataJpaTest
class DepartmentRepositoryTest {

	@Autowired
	DepartmentRepository departmentRepository;

	@Test
	public void saveDepartmentTest() {

		Department department = new Department();
		department.setId(1);
		department.setName("CSC");
		

		Department saveDepartment = departmentRepository.save(department);

		Assertions.assertNotNull(saveDepartment.getId());
		assertThat(saveDepartment).isNotNull();
		assertThat(saveDepartment.getId()).isGreaterThan(0);

	}

	@Test
	public void getDepartmentByIdTest() {

		Department department = new Department();
		department.setId(1);
		department.setName("CSC");

		departmentRepository.save(department);

		Department dept = departmentRepository.findById(1).get();

		assertNotNull(dept);
		assertEquals(department.getId(), dept.getId());
		assertThat(dept).isNotNull();
	}

	@Test
	public void findByNameTest() {

		Department department = new Department();
		department.setId(1);
		department.setName("CSC");
		

		departmentRepository.save(department);

		Department dept = departmentRepository.findByName(department.getName()).get();
		
		assertEquals(department.getName(), dept.getName());
		assertThat(department).isNotNull();
	}

	@Test
	public void getAllDepartmentsTest() {

		Department department = new Department();
		department.setId(2);
		department.setName("CSC");
		
		departmentRepository.save(department);
		List<Department> departmentlist = departmentRepository.findAll();

		assertThat(departmentlist).isNotNull();
		assertThat(departmentlist.size()).isEqualTo(1);

	}

	@Test
	public void updateDepartmentTest() {

		Department department = new Department();
		department.setId(2);
		department.setName("CSC");
		
       departmentRepository.save(department);

		Department saveDepartment = departmentRepository.getById(department.getId());
		saveDepartment.setName("java");
		
		Department updateEmployee = departmentRepository.save(saveDepartment);

		assertThat(updateEmployee.getName()).isEqualTo("java");
	}

	@Test
	public void deleteDepartmentTest() {

		Department department = new Department();
		department.setId(3);
		department.setName("CSC");
	
		departmentRepository.save(department);

		departmentRepository.delete(department);

		Optional<Department> e1 = departmentRepository.findById(3);
		assertThat(e1).isEmpty();

	}
}

