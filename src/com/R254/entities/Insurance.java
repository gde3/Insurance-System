package com.R254.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "insurance")
public class Insurance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int insuranceId;

	@Column
	private String insuranceName;

	@Column
	private double insurancePrice;

	@Column
	private String description;

	public int getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public double getInsurancePrice() {
		return insurancePrice;
	}

	public void setInsurancePrice(double insurancePrice) {
		this.insurancePrice = insurancePrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
