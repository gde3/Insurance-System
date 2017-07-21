package com.R254.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;

	@Column
	private String customerName;

	@Column
	private String customerGender;

	@Column
	private double loan;

	@ManyToMany
	@JoinTable(name = "Cust_Insur", joinColumns = { @JoinColumn(name = "CustId") }, inverseJoinColumns = {
			@JoinColumn(name = "InsurId") })
	private Set<Insurance> insurancesOrdered = new HashSet<>();

	@OneToMany
	@JoinColumn(name = "CustId")
	private Set<Query> queriesCreated = new HashSet<>();

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerGender() {
		return customerGender;
	}

	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}

	public double getLoan() {
		return loan;
	}

	public void setLoan(double loan) {
		this.loan = loan;
	}

	public Set<Insurance> getInsurancesOrdered() {
		return insurancesOrdered;
	}

	public void setInsurancesOrdered(Set<Insurance> insurancesOrdered) {
		this.insurancesOrdered = insurancesOrdered;
	}

	public Set<Query> getQueriesCreated() {
		return queriesCreated;
	}

	public void setQueriesCreated(Set<Query> queriesCreated) {
		this.queriesCreated = queriesCreated;
	}

}
