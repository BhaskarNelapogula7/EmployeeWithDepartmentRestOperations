package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	public Optional<Department> findByName(String name);
}
