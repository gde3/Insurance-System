package com.R254.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int employeeId;

	@Column
	private String employeeName;

	@Column
	private String employeeGender;

	@OneToMany
	@JoinColumn(name = "EmpId")
	private Set<Insurance> insurancesGenerated = new HashSet<>();

	@OneToMany
	@JoinColumn(name = "EmpId")
	private Set<Customer> loaners = new HashSet<>();

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeGender() {
		return employeeGender;
	}

	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}

	public Set<Insurance> getInsurancesGenerated() {
		return insurancesGenerated;
	}

	public void setInsurancesGenerated(Set<Insurance> insurancesGenerated) {
		this.insurancesGenerated = insurancesGenerated;
	}

	public Set<Customer> getLoaners() {
		return loaners;
	}

	public void setLoaners(Set<Customer> loaners) {
		this.loaners = loaners;
	}

}
