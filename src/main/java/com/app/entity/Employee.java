package com.app.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="empid")
	private Integer id;
	private String name;
	private String designation;
	private String techStack;
	@Temporal(TemporalType.TIMESTAMP)
	private Date joinDate;
	private double salary;
	private String status;

	//@JsonManagedReference
	//@JsonIgnore
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "Emp_Dep_tab", joinColumns = @JoinColumn(name = "Employee_id"),
	inverseJoinColumns = @JoinColumn(name = "Department_id"))
	private Set<Department> department = new HashSet<>();

	public Employee() {
		
	}

	public Employee(Integer id, String name, String designation, String techStack, Date joinDate, double salary,
			String status, Set<Department> department) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.techStack = techStack;
		this.joinDate = joinDate;
		this.salary = salary;
		this.status = status;
		this.department = department;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getTechStack() {
		return techStack;
	}

	public void setTechStack(String techStack) {
		this.techStack = techStack;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Department> getDepartment() {
		return department;
	}

	public void setDepartment(Set<Department> department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", designation=" + designation + ", techStack=" + techStack
				+ ", joinDate=" + joinDate + ", salary=" + salary + ", status=" + status + ", department=" + department
				+ "]";
	}

}
